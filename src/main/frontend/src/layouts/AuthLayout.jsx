import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { initUserInfo } from "../reducers/User/userInfoSlice";
import MainLayout from "./MainLayout";
import Axios from "../utils/Axios";
import { message } from "antd";

export default function AuthLayout({ children }) {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  useEffect(() => {
    Axios.get("/auth/me")
      .then((res) => {
        const username = res.data?.data?.username;
        dispatch(initUserInfo({ username }));
      })
      .catch((error) => {
        const { status } = error.response;
        navigate("/sign-in");
        if (status !== 401) {
          message.error("Something went wrong with the server");
        }
      });
  }, []);

  return <MainLayout>{children}</MainLayout>;
}
