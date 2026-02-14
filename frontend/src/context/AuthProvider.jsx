import React, { useState } from 'react';
import { AuthContext } from './AuthContext';
import { authApi } from '../api/authApi';
import { tokenStorage } from '../utils/tokenStorage';

export const AuthProvider = ({ children }) => {
  // Initialize state by checking auth immediately (no useEffect needed)
  const [isAuthenticated, setIsAuthenticated] = useState(() => {
    const token = tokenStorage.getToken();
    const userData = tokenStorage.getUserData();
    return !!(token && userData);
  });

  const [user, setUser] = useState(() => {
    const userData = tokenStorage.getUserData();
    return userData || null;
  });

  const [loading] = useState(false);

  const login = async (username, password) => {
    try {
      const response = await authApi.login(username, password);
      
      // Save token and user data
      tokenStorage.saveToken(response.token);
      tokenStorage.saveUserData({
        userId: response.userId,
        username: response.username,
        role: response.role
      });

      // Update state
      setIsAuthenticated(true);
      setUser({
        userId: response.userId,
        username: response.username,
        role: response.role
      });

      return { success: true };
    } catch (error) {
      const errorMessage = error.response?.data?.error || 'Login failed';
      return { success: false, error: errorMessage };
    }
  };

  const register = async (username, email, password) => {
    try {
      const response = await authApi.register(username, email, password);
      
      // Save token and user data
      tokenStorage.saveToken(response.token);
      tokenStorage.saveUserData({
        userId: response.userId,
        username: response.username,
        role: response.role
      });

      // Update state
      setIsAuthenticated(true);
      setUser({
        userId: response.userId,
        username: response.username,
        role: response.role
      });

      return { success: true };
    } catch (error) {
      const errorMessage = error.response?.data?.error || 'Registration failed';
      return { success: false, error: errorMessage };
    }
  };

  const logout = () => {
    tokenStorage.clearAll();
    setIsAuthenticated(false);
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{
      isAuthenticated,
      user,
      loading,
      login,
      register,
      logout
    }}>
      {children}
    </AuthContext.Provider>
  );
};