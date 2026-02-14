import React from 'react';
import { motion as Motion } from 'framer-motion';

const Button = ({
  children,
  onClick,
  type = 'button',
  variant = 'primary',
  disabled = false,
  loading = false,
  className = ''
}) => {
  const baseStyles =
    "w-full py-3.5 px-6 rounded-xl font-semibold text-sm tracking-wide transition-all duration-200 flex items-center justify-center gap-2 shadow-sm";

  const variants = {
    primary:
      "bg-primary hover:bg-blue-700 text-white shadow-blue-500/20 hover:shadow-blue-500/30 hover:shadow-md",
    secondary:
      "bg-secondary hover:bg-green-700 text-white shadow-emerald-500/20 hover:shadow-emerald-500/30 hover:shadow-md",
    outline:
      "border-2 border-primary text-primary hover:bg-primary hover:text-white bg-transparent",
  };

  return (
    <Motion.button
      whileHover={{ scale: disabled ? 1 : 1.01 }}
      whileTap={{ scale: disabled ? 1 : 0.98 }}
      type={type}
      onClick={onClick}
      disabled={disabled || loading}
      className={`${baseStyles} ${variants[variant]} ${
        disabled || loading ? 'opacity-50 cursor-not-allowed' : 'cursor-pointer'
      } ${className}`}
    >
      {loading ? (
        <>
          <svg
            className="animate-spin h-5 w-5 text-white"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
          >
            <circle
              className="opacity-25"
              cx="12"
              cy="12"
              r="10"
              stroke="currentColor"
              strokeWidth="4"
            ></circle>
            <path
              className="opacity-75"
              fill="currentColor"
              d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
            ></path>
          </svg>
          Loading...
        </>
      ) : (
        children
      )}
    </Motion.button>
  );
};

export default Button;
