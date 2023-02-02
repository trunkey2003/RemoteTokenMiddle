import {useEffect} from "react";
import { Axios } from "../util/Axios";

export default function Login() {
  const handleSubmitForm = (e) => {
    e.preventDefault();
    Axios.post("/api/v1/auth/login", {
      username: e.target.username.value,
      password: e.target.password.value,
    })
      .then(() => {
        alert("success");
        window.location.href = "/success";
      })
      .catch(() => {
        alert("fail");
      });
  };

  useEffect(() => {
    Axios.get("/api/v1/auth/check-auth")
      .then((res) => {
        window.location.href = "/success";
      });
  }, []);

  return (
    <div className="w-full flex flex-col items-center justify-center px-6 py-8 mx-auto h-screen lg:py-0">
      <div className="w-full bg-[#1a202c] rounded-lg shadow md:mt-0 sm:max-w-md xl:p-0">
        <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-300 md:text-2xl text-center">
            Đăng Nhập
          </h1>
          <form onSubmit={(e) => handleSubmitForm(e)}>
            <div className="mb-4">
              <label
                htmlFor="username"
                className="block text-sm font-medium text-gray-300"
              >
                Username
              </label>

              <input
                type="username"
                name="username"
                id="username"
                className="w-full p-2"
                placeholder="username"
              />
            </div>
            <div className="mb-4">
              <label
                htmlFor="password"
                className="block text-sm font-medium text-gray-300"
              >
                Password
              </label>

              <input
                type="password"
                name="password"
                id="password"
                className="w-full p-2"
                placeholder="password"
              />
            </div>
            <button type="submit" className="text-white bg-blue-600 px-10 py-2">
              Đăng Nhập
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
