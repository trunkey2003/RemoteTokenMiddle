import { message } from "antd";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Axios from "../../utils/Axios";

export default function Home() {
  const navigate = useNavigate();
  
  useEffect(() => {
    Axios.get("/auth/me")
      .then(() => {
        navigate("/dashboard");
      })
      .catch((error) => {
        const {status} = error.response;
        if (status !== 401) {
          message.error("Something went wrong with the server");
        }
        navigate("/sign-in");
      });
  }, []);
  return <></>;
}
