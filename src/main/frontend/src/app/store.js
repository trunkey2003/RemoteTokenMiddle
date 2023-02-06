import { configureStore } from '@reduxjs/toolkit';
import userInfoReducer from '../reducers/User/userInfoSlice';

export const store = configureStore({
  reducer: {
    userInfo: userInfoReducer,
  },
})