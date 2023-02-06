import './App.css';
import { Provider } from 'react-redux'
import { store } from './app/store';

import { Route, Routes } from "react-router-dom";
import { BrowserRouter } from "react-router-dom";

import Login from './pages/Auth/Login';
import i18n from './translation/i18n';

import { I18nextProvider } from 'react-i18next';
import Dashboard from './pages/Dashboard/Dashboard';
import Home from './pages/Home/Home';

function App() {
  return (
    <Provider store={store}>
      <I18nextProvider i18n={i18n}>
        <BrowserRouter>
          <Routes>
            <Route exact path='/' element={<Home />}></Route>
            <Route exact path='/sign-in' element={<Login />}></Route>
            <Route exact path='/dashboard' element={<Dashboard />}></Route>
          </Routes>
        </BrowserRouter>
      </I18nextProvider>
    </Provider>
  );
}

export default App;
