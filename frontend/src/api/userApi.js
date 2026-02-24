import axiosInstance from './axios.config';

export const userApi = {
  // Create user profile
  createProfile: async (userId, email) => {
    const response = await axiosInstance.post('/user/profile/create', {
      userId,
      email
    });
    return response.data;
  },

  // Get user profile
  getProfile: async () => {
    const response = await axiosInstance.get('/user/profile');
    return response.data;
  },

  // Update user profile
  updateProfile: async (profileData) => {
    const response = await axiosInstance.put('/user/profile', profileData);
    return response.data;
  },

  // Delete profile picture
  deleteProfilePicture: async () => {
    const response = await axiosInstance.delete('/user/profile/picture');
    return response.data;
  }
};