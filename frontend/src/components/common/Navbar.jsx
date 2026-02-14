import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/useAuth';
import { motion as Motion} from 'framer-motion';

const Navbar = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <nav className="bg-white shadow-md">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center h-16">
          {/* Logo */}
          <div className="flex items-center">
            <Motion.div 
              whileHover={{ scale: 1.05 }}
              className="text-2xl font-bold text-primary cursor-pointer"
              onClick={() => navigate('/dashboard')}
            >
              💰 Fintech App
            </Motion.div>
          </div>

          {/* User Info & Logout */}
          <div className="flex items-center space-x-4">
            <div className="text-right">
              <p className="text-sm font-medium text-gray-700">
                {user?.username}
              </p>
              <p className="text-xs text-gray-500">
                {user?.role}
              </p>
            </div>
            <Motion.button
              whileHover={{ scale: 1.05 }}
              whileTap={{ scale: 0.95 }}
              onClick={handleLogout}
              className="bg-error hover:bg-red-600 text-white px-4 py-2 rounded-lg text-sm font-medium transition-colors"
            >
              Logout
            </Motion.button>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;