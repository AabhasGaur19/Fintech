// Token Storage Utility
const TOKEN_KEY = 'authToken';
const USER_DATA_KEY = 'userData';

export const tokenStorage = {
  // Save token to localStorage
  saveToken: (token) => {
    localStorage.setItem(TOKEN_KEY, token);
  },

  // Get token from localStorage
  getToken: () => {
    return localStorage.getItem(TOKEN_KEY);
  },

  // Remove token from localStorage
  removeToken: () => {
    localStorage.removeItem(TOKEN_KEY);
  },

  // Save user data to localStorage
  saveUserData: (userData) => {
    localStorage.setItem(USER_DATA_KEY, JSON.stringify(userData));
  },

  // Get user data from localStorage
  getUserData: () => {
    const data = localStorage.getItem(USER_DATA_KEY);
    return data ? JSON.parse(data) : null;
  },

  // Clear all auth data
  clearAll: () => {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(USER_DATA_KEY);
  }
};