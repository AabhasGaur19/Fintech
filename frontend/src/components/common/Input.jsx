import React from 'react';
import { motion as Motion} from 'framer-motion';

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
    <div className="mb-4">
      {label && (
        <label className="block text-gray-700 text-sm font-medium mb-2">
          {label} {required && <span className="text-error">*</span>}
        </label>
      )}
      <Motion.input
        whileFocus={{ scale: 1.01 }}
        type={type}
        name={name}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        className={`w-full px-4 py-3 border rounded-lg focus:outline-none focus:ring-2 transition-all ${
          error 
            ? 'border-error focus:ring-error' 
            : 'border-gray-300 focus:ring-primary focus:border-primary'
        }`}
      />
      {error && (
        <Motion.p 
          initial={{ opacity: 0, y: -10 }}
          animate={{ opacity: 1, y: 0 }}
          className="text-error text-sm mt-1"
        >
          {error}
        </Motion.p>
      )}
    </div>
  );
};

export default Input;