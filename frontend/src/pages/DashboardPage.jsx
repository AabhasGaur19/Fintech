import React from 'react';
import { motion as Motion} from 'framer-motion';
import { useAuth } from '../context/useAuth';
import Navbar from '../components/common/Navbar';

const DashboardPage = () => {
  const { user } = useAuth();

  const containerVariants = {
    hidden: { opacity: 0 },
    visible: {
      opacity: 1,
      transition: {
        staggerChildren: 0.1
      }
    }
  };

  const itemVariants = {
    hidden: { opacity: 0, y: 20 },
    visible: { opacity: 1, y: 0 }
  };

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        {/* Welcome Section */}
        <Motion.div
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          className="mb-8"
        >
          <h1 className="text-4xl font-bold text-gray-800 mb-2">
            Welcome back, {user?.username}! 👋
          </h1>
          <p className="text-gray-600">Here's what's happening with your account today.</p>
        </Motion.div>

        {/* User Info Cards */}
        <Motion.div
          variants={containerVariants}
          initial="hidden"
          animate="visible"
          className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-8"
        >
          {/* Account Status Card */}
          <Motion.div variants={itemVariants} className="bg-white rounded-xl shadow-md p-6 border-l-4 border-primary">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm font-medium">Account Status</p>
                <p className="text-2xl font-bold text-gray-800 mt-1">Active</p>
              </div>
              <div className="bg-primary bg-opacity-10 p-3 rounded-full">
                <svg className="w-8 h-8 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
              </div>
            </div>
          </Motion.div>

          {/* User ID Card */}
          <Motion.div variants={itemVariants} className="bg-white rounded-xl shadow-md p-6 border-l-4 border-secondary">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm font-medium">User ID</p>
                <p className="text-2xl font-bold text-gray-800 mt-1">#{user?.userId}</p>
              </div>
              <div className="bg-secondary bg-opacity-10 p-3 rounded-full">
                <svg className="w-8 h-8 text-secondary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M10 6H5a2 2 0 00-2 2v9a2 2 0 002 2h14a2 2 0 002-2V8a2 2 0 00-2-2h-5m-4 0V5a2 2 0 114 0v1m-4 0a2 2 0 104 0m-5 8a2 2 0 100-4 2 2 0 000 4zm0 0c1.306 0 2.417.835 2.83 2M9 14a3.001 3.001 0 00-2.83 2M15 11h3m-3 4h2" />
                </svg>
              </div>
            </div>
          </Motion.div>

          {/* Role Card */}
          <Motion.div variants={itemVariants} className="bg-white rounded-xl shadow-md p-6 border-l-4 border-purple-500">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm font-medium">Role</p>
                <p className="text-2xl font-bold text-gray-800 mt-1">{user?.role}</p>
              </div>
              <div className="bg-purple-100 p-3 rounded-full">
                <svg className="w-8 h-8 text-purple-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
              </div>
            </div>
          </Motion.div>
        </Motion.div>

        {/* Coming Soon Section */}
        <Motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.5 }}
          className="grid grid-cols-1 md:grid-cols-2 gap-6"
        >
          {/* Balance Card */}
          <div className="bg-gradient-to-br from-blue-500 to-purple-600 rounded-xl shadow-lg p-8 text-white">
            <h3 className="text-lg font-semibold mb-2">Wallet Balance</h3>
            <p className="text-4xl font-bold mb-4">Coming Soon</p>
            <p className="text-blue-100">Your wallet balance will be displayed here</p>
          </div>

          {/* Recent Activity Card */}
          <div className="bg-gradient-to-br from-green-500 to-teal-600 rounded-xl shadow-lg p-8 text-white">
            <h3 className="text-lg font-semibold mb-2">Recent Activity</h3>
            <p className="text-4xl font-bold mb-4">Coming Soon</p>
            <p className="text-green-100">Your recent transactions will appear here</p>
          </div>
        </Motion.div>

        {/* Info Message */}
        <Motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.7 }}
          className="mt-8 bg-blue-50 border border-blue-200 rounded-xl p-6"
        >
          <div className="flex items-start">
            <svg className="w-6 h-6 text-primary mr-3 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            <div>
              <h4 className="text-lg font-semibold text-gray-800 mb-1">Welcome to Fintech App!</h4>
              <p className="text-gray-600">
                You've successfully logged in. More features like wallet, trading, and portfolio management are coming soon.
              </p>
            </div>
          </div>
        </Motion.div>
      </div>
    </div>
  );
};

export default DashboardPage;