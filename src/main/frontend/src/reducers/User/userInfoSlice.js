import { createSlice } from '@reduxjs/toolkit'

const initialState = {userName: null};

export const userInfoSlice = createSlice({
  name: 'userInfo',
  initialState,
  reducers: {
    initUserInfo (state, action) {
      return {...state, ...action.payload};
    },
  },
})

export const { initUserInfo } = userInfoSlice.actions

export default userInfoSlice.reducer