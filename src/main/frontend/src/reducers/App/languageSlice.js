import { createSlice } from '@reduxjs/toolkit'

const languages = [
    {id: '0', name: "English"},
    {id: '1', name: "Tiếng Việt"},
]

const initialState = languages[0];

export const languageSlice = createSlice({
  name: 'language',
  initialState,
  reducers: {
    changeLanguageByID(state, action) {
        const id = action.payload;
        return languages[id];
    }
  },
})

export const { initLanguage } = languageSlice.actions

export default languageSlice.reducer