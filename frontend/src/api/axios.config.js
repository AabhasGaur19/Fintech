import axios from 'axios';
import { tokenStorage } from '../utils/tokenStorage';

// Create axios instance
const axiosInstance = axios.create({
  baseURL: 'http://localhost:8081',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
});

// Request interceptor - Add token to every request
axiosInstance.interceptors.request.use(
  (config) => {
    const token = tokenStorage.getToken();
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor - Handle errors globally
axiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    // Handle 401 Unauthorized (token expired)
    if (error.response && error.response.status === 401) {
      tokenStorage.clearAll();
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;