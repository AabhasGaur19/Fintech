import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { authApi } from '../api/authApi';
import { tokenStorage } from '../utils/tokenStorage';

// Async thunk for login
export const loginUser = createAsyncThunk(
  'auth/login',
  async ({ username, password }, { rejectWithValue }) => {
    try {
      const response = await authApi.login(username, password);
      
      // Save to localStorage
      tokenStorage.saveToken(response.token);
      tokenStorage.saveUserData({
        userId: response.userId,
        username: response.username,
        role: response.role,
      });

      return {
        userId: response.userId,
        username: response.username,
        role: response.role,
      };
    } catch (error) {
      const errorMessage = error.response?.data?.error || 'Login failed';
      return rejectWithValue(errorMessage);
    }
  }
);

// Async thunk for register
export const registerUser = createAsyncThunk(
  'auth/register',
  async ({ username, email, password }, { rejectWithValue }) => {
    try {
      const response = await authApi.register(username, email, password);
      
      // Save to localStorage
      tokenStorage.saveToken(response.token);
      tokenStorage.saveUserData({
        userId: response.userId,
        username: response.username,
        role: response.role,
      });

      return {
        userId: response.userId,
        username: response.username,
        role: response.role,
      };
    } catch (error) {
      const errorMessage = error.response?.data?.error || 'Registration failed';
      return rejectWithValue(errorMessage);
    }
  }
);

const authSlice = createSlice({
  name: 'auth',
  initialState: {
    isAuthenticated: false,
    user: null,
    loading: false,
    error: null,
  },
  reducers: {
    logout: (state) => {
      tokenStorage.clearAll();
      state.isAuthenticated = false;
      state.user = null;
      state.error = null;
    },
    checkAuth: (state) => {
      const token = tokenStorage.getToken();
      const userData = tokenStorage.getUserData();

      if (token && userData) {
        state.isAuthenticated = true;
        state.user = userData;
      } else {
        state.isAuthenticated = false;
        state.user = null;
      }
    },
    clearError: (state) => {
      state.error = null;
    },
  },
  extraReducers: (builder) => {
    // Login
    builder
      .addCase(loginUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(loginUser.fulfilled, (state, action) => {
        state.loading = false;
        state.isAuthenticated = true;
        state.user = action.payload;
        state.error = null;
      })
      .addCase(loginUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });

    // Register
    builder
      .addCase(registerUser.pending, (state) => {
        state.loading = true;
        state.error = null;
      })
      .addCase(registerUser.fulfilled, (state, action) => {
        state.loading = false;
        state.isAuthenticated = true;
        state.user = action.payload;
        state.error = null;
      })
      .addCase(registerUser.rejected, (state, action) => {
        state.loading = false;
        state.error = action.payload;
      });
  },
});

export const { logout, checkAuth, clearError } = authSlice.actions;
export default authSlice.reducer;
