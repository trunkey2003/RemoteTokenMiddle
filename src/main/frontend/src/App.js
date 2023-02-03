import './App.css';
// import 'antd/dist/reset.css';

import { Route, Routes } from "react-router-dom";
import { BrowserRouter } from "react-router-dom";

import Login from './pages/Auth/Login';
import i18n from './translation/i18n';

import { I18nextProvider } from 'react-i18next';

function App() {
  return (
    <I18nextProvider i18n={i18n}>
      <BrowserRouter>
        <Routes>
          <Route exact path='/' element={<Login />}></Route>
        </Routes>
      </BrowserRouter>
    </I18nextProvider>
  );
}

export default App;
