import React from 'react';
import { motion as Motion } from 'framer-motion';
import { useSelector } from 'react-redux';
import Navbar from '../components/common/Navbar';

const DashboardPage = () => {
  const { user } = useSelector((state) => state.auth);

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
    <div className="min-h-screen bg-slate-50">
      <Navbar />

      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        {/* Welcome Section */}
        <Motion.div
          initial={{ opacity: 0, y: -20 }}
          animate={{ opacity: 1, y: 0 }}
          className="mb-8"
        >
          <h1 className="text-3xl font-bold text-slate-800 tracking-tight">
            {'Welcome back, '}{user?.username}{'!'}
          </h1>
          <p className="text-slate-500 mt-1">{"Here's what's happening with your account today."}</p>
        </Motion.div>

        {/* User Info Cards */}
        <Motion.div
          variants={containerVariants}
          initial="hidden"
          animate="visible"
          className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-5 mb-8"
        >
          {/* Account Status Card */}
          <Motion.div
            variants={itemVariants}
            className="bg-white rounded-2xl p-6 border border-slate-200/80 hover:border-slate-300 transition-colors shadow-sm"
          >
            <div className="flex items-center justify-between">
              <div>
                <p className="text-xs font-semibold text-slate-400 uppercase tracking-wider">Account Status</p>
                <p className="text-2xl font-bold text-slate-800 mt-2">Active</p>
              </div>
              <div className="w-12 h-12 rounded-2xl bg-blue-50 flex items-center justify-center">
                <svg className="w-6 h-6 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
              </div>
            </div>
            <div className="mt-4 flex items-center gap-1.5">
              <div className="w-2 h-2 rounded-full bg-secondary" />
              <span className="text-xs font-medium text-secondary">Verified</span>
            </div>
          </Motion.div>

          {/* User ID Card */}
          <Motion.div
            variants={itemVariants}
            className="bg-white rounded-2xl p-6 border border-slate-200/80 hover:border-slate-300 transition-colors shadow-sm"
          >
            <div className="flex items-center justify-between">
              <div>
                <p className="text-xs font-semibold text-slate-400 uppercase tracking-wider">User ID</p>
                <p className="text-2xl font-bold text-slate-800 mt-2">#{user?.userId}</p>
              </div>
              <div className="w-12 h-12 rounded-2xl bg-emerald-50 flex items-center justify-center">
                <svg className="w-6 h-6 text-secondary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M10 6H5a2 2 0 00-2 2v9a2 2 0 002 2h14a2 2 0 002-2V8a2 2 0 00-2-2h-5m-4 0V5a2 2 0 114 0v1m-4 0a2 2 0 104 0m-5 8a2 2 0 100-4 2 2 0 000 4zm0 0c1.306 0 2.417.835 2.83 2M9 14a3.001 3.001 0 00-2.83 2M15 11h3m-3 4h2" />
                </svg>
              </div>
            </div>
            <div className="mt-4 flex items-center gap-1.5">
              <div className="w-2 h-2 rounded-full bg-primary" />
              <span className="text-xs font-medium text-primary">Registered</span>
            </div>
          </Motion.div>

          {/* Role Card */}
          <Motion.div
            variants={itemVariants}
            className="bg-white rounded-2xl p-6 border border-slate-200/80 hover:border-slate-300 transition-colors shadow-sm"
          >
            <div className="flex items-center justify-between">
              <div>
                <p className="text-xs font-semibold text-slate-400 uppercase tracking-wider">Role</p>
                <p className="text-2xl font-bold text-slate-800 mt-2">{user?.role}</p>
              </div>
              <div className="w-12 h-12 rounded-2xl bg-amber-50 flex items-center justify-center">
                <svg className="w-6 h-6 text-amber-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
              </div>
            </div>
            <div className="mt-4 flex items-center gap-1.5">
              <div className="w-2 h-2 rounded-full bg-amber-500" />
              <span className="text-xs font-medium text-amber-600">Standard</span>
            </div>
          </Motion.div>
        </Motion.div>

        {/* Coming Soon Section */}
        <Motion.div
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.5 }}
          className="grid grid-cols-1 md:grid-cols-2 gap-5"
        >
          {/* Balance Card */}
          <div className="bg-primary rounded-2xl p-8 text-white relative overflow-hidden shadow-lg shadow-blue-500/20">
            <div className="absolute top-0 right-0 w-40 h-40 bg-white/5 rounded-full -translate-y-1/2 translate-x-1/3" />
            <div className="absolute bottom-0 left-0 w-32 h-32 bg-white/5 rounded-full translate-y-1/3 -translate-x-1/4" />
            <div className="relative z-10">
              <div className="flex items-center gap-2 mb-3">
                <svg className="w-5 h-5 text-blue-200" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M3 10h18M7 15h1m4 0h1m-7 4h12a3 3 0 003-3V8a3 3 0 00-3-3H6a3 3 0 00-3 3v8a3 3 0 003 3z" />
                </svg>
                <h3 className="text-sm font-semibold text-blue-100 uppercase tracking-wider">Wallet Balance</h3>
              </div>
              <p className="text-3xl font-bold mb-2">Coming Soon</p>
              <p className="text-blue-200 text-sm">Your wallet balance will be displayed here</p>
            </div>
          </div>

          {/* Recent Activity Card */}
          <div className="bg-secondary rounded-2xl p-8 text-white relative overflow-hidden shadow-lg shadow-emerald-500/20">
            <div className="absolute top-0 right-0 w-40 h-40 bg-white/5 rounded-full -translate-y-1/2 translate-x-1/3" />
            <div className="absolute bottom-0 left-0 w-32 h-32 bg-white/5 rounded-full translate-y-1/3 -translate-x-1/4" />
            <div className="relative z-10">
              <div className="flex items-center gap-2 mb-3">
                <svg className="w-5 h-5 text-emerald-200" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                </svg>
                <h3 className="text-sm font-semibold text-emerald-100 uppercase tracking-wider">Recent Activity</h3>
              </div>
              <p className="text-3xl font-bold mb-2">Coming Soon</p>
              <p className="text-emerald-200 text-sm">Your recent transactions will appear here</p>
            </div>
          </div>
        </Motion.div>

        {/* Info Message */}
        <Motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ delay: 0.7 }}
          className="mt-6 bg-white border border-slate-200/80 rounded-2xl p-6 shadow-sm"
        >
          <div className="flex items-start gap-3">
            <div className="w-10 h-10 rounded-xl bg-blue-50 flex items-center justify-center flex-shrink-0">
              <svg className="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
            </div>
            <div>
              <h4 className="text-sm font-semibold text-slate-800 mb-1">Welcome to Fintech App!</h4>
              <p className="text-slate-500 text-sm leading-relaxed">
                {"You've successfully logged in. More features like wallet, trading, and portfolio management are coming soon."}
              </p>
            </div>
          </div>
        </Motion.div>
      </div>
    </div>
  );
};

export default DashboardPage;