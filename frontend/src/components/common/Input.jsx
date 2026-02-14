import React from 'react';
import { motion as Motion } from 'framer-motion';

const Input = ({
  label,
  type = 'text',
  placeholder,
  value,
  onChange,
  error,
  required = false,
  name
}) => {
  return (
    <div className="mb-5">
      {label && (
        <label className="block text-slate-700 text-sm font-semibold mb-2 tracking-wide">
          {label} {required && <span className="text-error">*</span>}
        </label>
      )}
      <Motion.input
        whileFocus={{ scale: 1.005 }}
        type={type}
        name={name}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        className={`w-full px-4 py-3.5 bg-slate-50 border rounded-xl text-sm text-slate-800 placeholder-slate-400 focus:outline-none focus:ring-2 focus:bg-white transition-all duration-200 ${
          error
            ? 'border-error focus:ring-error'
            : 'border-slate-200 focus:ring-primary focus:border-primary hover:border-slate-300'
        }`}
      />
      {error && (
        <Motion.p
          initial={{ opacity: 0, y: -10 }}
          animate={{ opacity: 1, y: 0 }}
          className="text-error text-xs mt-1.5 font-medium flex items-center gap-1"
        >
          <svg className="w-3.5 h-3.5 flex-shrink-0" fill="currentColor" viewBox="0 0 20 20">
            <path fillRule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7 4a1 1 0 11-2 0 1 1 0 012 0zm-1-9a1 1 0 00-1 1v4a1 1 0 102 0V6a1 1 0 00-1-1z" clipRule="evenodd" />
          </svg>
          {error}
        </Motion.p>
      )}
    </div>
  );
};

export default Input;
