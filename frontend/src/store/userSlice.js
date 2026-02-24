import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { userApi } from '../api/userApi';

// Async thunk for fetching profile
export const fetchUserProfile = createAsyncThunk(
  'user/fetchProfile',
  async (_, { rejectWithValue }) => {
    try {
      const response = await userApi.getProfile();
      return response;
    } catch (error) {
      const errorMessage = error.response?.data?.error || 'Failed to fetch profile';
      return rejectWithValue(errorMessage);
    }
  }
);

// Async thunk for updating profile
export const updateUserProfile = createAsyncThunk(
  'user/updateProfile',
  async (profileData, { rejectWithValue }) => {
    try {
      const response = await userApi.updateProfile(profileData);
      return response;
    } catch (error) {
      const errorMessage = error.response?.data?.error || 'Failed to update profile';
      return rejectWithValue(errorMessage);
    }
  }
);

// Async thunk for creating profile
export const createUserProfile = createAsyncThunk(
  'user/createProfile',
  async ({ userId, email }, { rejectWithValue }) => {
    try {
      const response = await userApi.createProfile(userId, email);
      return response;
    } catch (error) {
      const errorMessage = error.response?.data?.error || 'Failed to create profile';
      return rejectWithValue(errorMessage);
    }
  }
);

const userSlice = createSlice({
  name: 'user',
  initialState: {
    profile: null,
    loading: false,
    error: null,
    updateSuccess: false,
  },
  reducers: {
    clearUserError: (state) => {
      state.error = null;
    },
    clearUpdateSuccess: (state) => {
      state.updateSuccess = false;
    },
  },
  extraReducers: (builder) => {
    // Fetch Profile
    builder
      .addCase(fetchUserProfile.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchUserProfile.fulfilled, (state, action) => {
        state.loading = false;
        state.profile = action.payload;
        state.error = null;
      })
      .addCase(fetchUserProfile.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });

    // Update Profile
    builder
      .addCase(updateUserProfile.pending, (state) => {
        state.loading = true;
        state.error = null;
        state.updateSuccess = false;
      })
      .addCase(updateUserProfile.fulfilled, (state) => {
        state.loading = false;
        state.updateSuccess = true;
        state.error = null;
      })
      .addCase(updateUserProfile.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
        state.updateSuccess = false;
      });

    // Create Profile
    builder
      .addCase(createUserProfile.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(createUserProfile.fulfilled, (state) => {
        state.loading = false;
        state.error = null;
      })
      .addCase(createUserProfile.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export const { clearUserError, clearUpdateSuccess } = userSlice.actions;
export default userSlice.reducer;