import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {
  updateUserProfile,
  clearUserError,
  clearUpdateSuccess,
} from '../../store/userSlice';
import { motion as Motion } from 'framer-motion';
import Input from '../common/Input';
import Button from '../common/Button';

/* ---------------------------------------------
   Helper: Generate Initial Form State
--------------------------------------------- */
const getInitialFormData = (profile) => ({
  firstName: profile?.firstName || '',
  lastName: profile?.lastName || '',
  themePreference: profile?.themePreference || 'LIGHT',
  preferredLanguage: profile?.preferredLanguage || 'EN',
  timezone: profile?.timezone || 'UTC',
  twoFactorEnabled: profile?.twoFactorEnabled || false,
  notificationEnabled: profile?.notificationEnabled ?? true,
});

const ProfileForm = ({ profile }) => {
  const dispatch = useDispatch();

  const { loading, error, updateSuccess } = useSelector(
    (state) => state.user
  );

  /* ---------------------------------------------
     Form State (Derived from Profile)
  --------------------------------------------- */
  const [formData, setFormData] = useState(() =>
    getInitialFormData(profile)
  );

  const [errors, setErrors] = useState({});

  /* ---------------------------------------------
     Reset Form When Profile Changes
  --------------------------------------------- */
  useEffect(() => {
    setFormData(getInitialFormData(profile));
  }, [profile]);

  /* ---------------------------------------------
     Clear Success Message After 3s
  --------------------------------------------- */
  useEffect(() => {
    if (updateSuccess) {
      const timer = setTimeout(() => {
        dispatch(clearUpdateSuccess());
      }, 3000);

      return () => clearTimeout(timer);
    }
  }, [updateSuccess, dispatch]);

  /* ---------------------------------------------
     Cleanup Errors on Unmount
  --------------------------------------------- */
  useEffect(() => {
    return () => {
      dispatch(clearUserError());
    };
  }, [dispatch]);

  /* ---------------------------------------------
     Input Handler
  --------------------------------------------- */
  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;

    setFormData((prev) => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value,
    }));

    if (errors[name]) {
      setErrors((prev) => ({ ...prev, [name]: '' }));
    }

    dispatch(clearUserError());
  };

  /* ---------------------------------------------
     Validation
  --------------------------------------------- */
  const validate = () => {
    const newErrors = {};

    if (formData.firstName.length > 50) {
      newErrors.firstName =
        'First name must not exceed 50 characters';
    }

    if (formData.lastName.length > 50) {
      newErrors.lastName =
        'Last name must not exceed 50 characters';
    }

    return newErrors;
  };

  /* ---------------------------------------------
     Submit Handler
  --------------------------------------------- */
  const handleSubmit = (e) => {
    e.preventDefault();

    const validationErrors = validate();

    if (Object.keys(validationErrors).length) {
      setErrors(validationErrors);
      return;
    }

    const dataToUpdate = {
      themePreference: formData.themePreference,
      preferredLanguage: formData.preferredLanguage,
      timezone: formData.timezone,
      twoFactorEnabled: formData.twoFactorEnabled,
      notificationEnabled: formData.notificationEnabled,
    };

    if (formData.firstName.trim()) {
      dataToUpdate.firstName = formData.firstName.trim();
    }

    if (formData.lastName.trim()) {
      dataToUpdate.lastName = formData.lastName.trim();
    }

    dispatch(updateUserProfile(dataToUpdate));
  };

  /* ---------------------------------------------
     UI
  --------------------------------------------- */
  return (
    <Motion.div
      initial={{ opacity: 0, y: 20 }}
      animate={{ opacity: 1, y: 0 }}
      className="bg-white rounded-2xl shadow-sm border border-slate-200/80 p-8"
    >
      <h2 className="text-2xl font-bold text-slate-800 mb-6">
        Profile Settings
      </h2>

      {/* Success Message */}
      {updateSuccess && (
        <Motion.div
          initial={{ opacity: 0, y: -8 }}
          animate={{ opacity: 1, y: 0 }}
          className="mb-6 p-3.5 bg-green-50 border border-green-200 rounded-xl text-green-700 text-sm font-medium flex items-center gap-2"
        >
          ✅ Profile updated successfully!
        </Motion.div>
      )}

      <form onSubmit={handleSubmit}>
        {/* Name */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-6">
          <Input
            label="First Name"
            name="firstName"
            value={formData.firstName}
            onChange={handleChange}
            error={errors.firstName}
            placeholder="Enter your first name"
          />

          <Input
            label="Last Name"
            name="lastName"
            value={formData.lastName}
            onChange={handleChange}
            error={errors.lastName}
            placeholder="Enter your last name"
          />
        </div>

        {/* Preferences */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-6">
          {/* Theme */}
          <div>
            <label className="block text-sm font-semibold mb-2">
              Theme
            </label>

            <select
              name="themePreference"
              value={formData.themePreference}
              onChange={handleChange}
              className="w-full px-4 py-3 rounded-xl border"
            >
              <option value="LIGHT">Light</option>
              <option value="DARK">Dark</option>
              <option value="AUTO">Auto</option>
            </select>
          </div>

          {/* Language */}
          <div>
            <label className="block text-sm font-semibold mb-2">
              Language
            </label>

            <select
              name="preferredLanguage"
              value={formData.preferredLanguage}
              onChange={handleChange}
              className="w-full px-4 py-3 rounded-xl border"
            >
              <option value="EN">English</option>
              <option value="HI">Hindi</option>
              <option value="ES">Spanish</option>
              <option value="FR">French</option>
            </select>
          </div>
        </div>

        {/* Timezone */}
        <div className="mb-6">
          <label className="block text-sm font-semibold mb-2">
            Timezone
          </label>

          <select
            name="timezone"
            value={formData.timezone}
            onChange={handleChange}
            className="w-full px-4 py-3 rounded-xl border"
          >
            <option value="UTC">UTC</option>
            <option value="Asia/Kolkata">
              Asia/Kolkata (IST)
            </option>
            <option value="America/New_York">
              America/New_York (EST)
            </option>
            <option value="Europe/London">
              Europe/London (GMT)
            </option>
            <option value="Asia/Tokyo">
              Asia/Tokyo (JST)
            </option>
          </select>
        </div>

        {/* Toggles */}
        <div className="space-y-4 mb-6">
          <label className="flex items-center gap-3">
            <input
              type="checkbox"
              name="twoFactorEnabled"
              checked={formData.twoFactorEnabled}
              onChange={handleChange}
            />
            Enable Two-Factor Authentication
          </label>

          <label className="flex items-center gap-3">
            <input
              type="checkbox"
              name="notificationEnabled"
              checked={formData.notificationEnabled}
              onChange={handleChange}
            />
            Enable Notifications
          </label>
        </div>

        {/* Error */}
        {error && (
          <Motion.div
            initial={{ opacity: 0, y: -8 }}
            animate={{ opacity: 1, y: 0 }}
            className="mb-6 p-3 bg-red-50 border border-red-200 rounded-xl text-red-600"
          >
            ❌ {error}
          </Motion.div>
        )}

        {/* Submit */}
        <Button type="submit" loading={loading} disabled={loading}>
          {loading ? 'Saving...' : 'Save Changes'}
        </Button>
      </form>
    </Motion.div>
  );
};

export default ProfileForm;