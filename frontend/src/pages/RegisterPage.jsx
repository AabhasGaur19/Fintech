import React from 'react';
import { motion as Motion} from 'framer-motion';
import RegisterForm from '../components/auth/RegisterForm';  // ✅ Removed curly braces

const RegisterPage = () => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-green-50 to-emerald-100 flex items-center justify-center p-4">
      <div className="w-full max-w-md">
        <Motion.div
          initial={{ opacity: 0, scale: 0.9 }}
          animate={{ opacity: 1, scale: 1 }}
          transition={{ duration: 0.3 }}
          className="text-center mb-8"
        >
          <h1 className="text-5xl font-bold text-secondary mb-2">💰</h1>
          <h1 className="text-4xl font-bold text-gray-800">Fintech App</h1>
        </Motion.div>
        
        <RegisterForm />
      </div>
    </div>
  );
};

export default RegisterPage;