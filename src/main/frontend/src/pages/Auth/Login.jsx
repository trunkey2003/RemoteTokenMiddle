import { useState, useEffect } from "react";
import { Link, useHistory } from "react-router-dom";
import { Form, Input, Button, message } from "antd";
import { useTranslation } from "react-i18next";
import { useNavigate } from "react-router-dom";
import Axios from "../../utils/Axios";

export default function SignIn() {
  const navigate = useNavigate();

  const { t } = useTranslation();
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const submitForm = ({ username, password }) => {
    setLoading(true);
    Axios.post("/auth/sign-in", { username, password })
      .then((res) => {
        message.success(t("login.loginMessageSuccess"));
        navigate("/dashboard");
      })
      .catch(() => {
        message.error(t("login.loginMessageError"));
      })
      .finally(() => {
        setLoading(false);
      });
  };

  useEffect(() => {
    Axios.get("/auth/me").then((res) => {
      navigate("/dashboard");
    });
  }, [])
  

  return (
    <div
      className="w-full flex flex-col items-center justify-center px-6 py-8 mx-auto h-screen lg:py-0 bg-cover bg-center"
      style={{
        backgroundImage: "url('/images/login-bg.webp')",
      }}
    >
      <div className="absolute top-0 left-0 h-full w-full flex items-center justify-center backdrop-blur-sm">
        <div className="w-full bg-white border-2 border-blue-800 rounded-lg shadow md:mt-0 sm:max-w-md xl:py-5">
          <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
            <Link
              to="/"
              className="flex items-center mb-6 justify-center hover:opacity-80"
            >
              <img className="h-[50px]" src="/images/logo.webp" alt="logo" />
            </Link>
            <h1 className="text-xl font-bold leading-tight tracking-tight text-blue-800 md:text-2xl text-center">
              {t("login.title")}
            </h1>
            <Form
              requiredMark={false}
              layout="vertical"
              className="space-y-4"
              form={form}
              onFinish={submitForm}
            >
              <Form.Item
                className="mb-0"
                label={
                  <label
                    htmlFor="username"
                    className="block text-sm font-medium text-blue-800"
                  >
                    {t("login.labelUsername")}
                  </label>
                }
                name={"username"}
                rules={[
                  {
                    required: true,
                    message: t("login.validateTextUsername"),
                  },
                ]}
              >
                <Input
                  size="large"
                  type="username"
                  name="username"
                  id="username"
                  className=""
                  placeholder={t("login.placeHolderUsername")}
                />
              </Form.Item>
              <Form.Item
                className="mb-0"
                label={
                  <label
                    htmlFor="password"
                    className="block text-sm font-medium text-blue-800"
                  >
                    Mật Khẩu
                  </label>
                }
                name="password"
                rules={[
                  {
                    required: true,
                    message: t("login.validateTextPassword"),
                  },
                ]}
              >
                <Input.Password
                  size="large"
                  type="password"
                  name="password"
                  id="password"
                  placeholder="••••••••"
                  className=""
                  required=""
                />
              </Form.Item>
              {/* <div className="flex items-center justify-end mb-4">
            <a href="#" className="text-sm font-medium text-primary-600 hover:underline">
              Forgot password?
            </a>
          </div> */}
              <Form.Item>
                <Button
                  aria-label="Sign-in"
                  htmlType="submit"
                  role="button"
                  size="large"
                  className="w-full rounded-md bg-blue-800"
                  loading={loading}
                  type="primary"
                >
                  {t("login.buttonSignInText")}
                </Button>
              </Form.Item>

              {/* <p className="mt-5 text-sm font-light text-gray-500">
                Don’t have an account yet?{" "}
                <Link
                  to={"/sign-up"}
                  className="font-medium text-primary-600 hover:underline"
                >
                  Sign up
                </Link>
              </p> */}
            </Form>
          </div>
        </div>
      </div>
    </div>
  );
}
