import axiosInstance from './axios.config';

export const authApi = {
  // Health check
  healthCheck: async () => {
    const response = await axiosInstance.get('/auth/health');
    return response.data;
  },

  // Register new user
  register: async (username, email, password) => {
    const response = await axiosInstance.post('/auth/register', {
      username,
      email,
      password
    });
    return response.data;
  },

  // Login user
  login: async (username, password) => {
    const response = await axiosInstance.post('/auth/login', {
      username,
      password
    });
    return response.data;
  }
};