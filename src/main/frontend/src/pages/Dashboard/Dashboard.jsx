import { useEffect, useState } from "react";
import AuthLayout from "../../layouts/AuthLayout";
import { Form, Select, Input, Button, Spin } from "antd";
import Axios from "../../utils/Axios";
import {
  isValidPhoneNumber,
  isValidEmail,
  handleInputNumber,
} from "../../utils/";

const initDataState = {
  certificateAuthorityInfo: [],
  certificateProfileInfo: [],
  certificatePurposeInfo: []
};

const { Option } = Select;

export default function Dashboard() {
  const [data, setData] = useState(initDataState);
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    handleFetchData(initDataState);
  }, []);

  const handleFetchData = async (refetch) => {
    setLoading(true);
    //refetch có tác dụng làm mới dữ liệu khi có sự thay đổi
    // sở dĩ không sử dụng state để lưu dữ liệu để tăng hiệu năng code bớt re-render
    let data = {};

    try {
      if (refetch["certificateAuthorityInfo"]) {
        const authorityInfoParams = new URLSearchParams({
          language: 0,
        });
        const certificateAuthorityInfo = await Axios.get(
          "/certificate/getCertificateAuthorityForTMSRA?" +
            authorityInfoParams.toString()
        );
        data["certificateAuthorityInfo"] =
          certificateAuthorityInfo.data.data;
      };
  
      console.log(form.getFieldValue("certificateAuthorityCode") && refetch["certificateAuthorityInfo"])
  
      if (form.getFieldValue("certificateAuthorityCode") && refetch["certificateAuthorityInfo"]) {
        form.setFieldValue("certificateProfileCode", null)
        const profileInfoParams = new URLSearchParams({
          language: 0,
          certificateAuthorityCode: form.getFieldValue("certificateAuthorityCode"),
        });
        const certificateProfileInfo = await Axios.get(
          "/certificate/getCertificateProfileForTMSRA?" +
            profileInfoParams.toString()
        );
        data["certificateProfileInfo"] =
          certificateProfileInfo.data.data;
        console.log(certificateProfileInfo.data.data)
      };
  
      if (form.getFieldValue("certificateAuthorityCode") && refetch["certificatePurposeInfo"]) {
        form.setFieldValue("certificatePurposeCode", null)
        const purposeInfoParams = new URLSearchParams({
          language: 0,
          certificateAuthorityCode: form.getFieldValue("certificateAuthorityCode"),
        });
        const certificatePurposeInfo = await Axios.get(
          "/certificate/getCertificatePurposeForTMSRA?" +
            purposeInfoParams.toString()
        );
        data["certificatePurposeInfo"] =
          certificatePurposeInfo.data.data;
      };
      setData(data);
      setLoading(false)
    } catch {
      setLoading(false)
    }
  };

  return (
    <AuthLayout>
      <div className="pt-[50px] md:pt-[80px]">
        <div className="w-full lg:max-w-[1400px] p-5 lg:p-16 bg-white mx-auto">
          <Form layout="vertical" form={form}>
            <div className="md:grid md:grid-cols-2 gap-6">
              <Form.Item
                name="certificateAuthorityCode"
                label={
                  <label
                    htmlFor="certificateAuthorityCode"
                    className="block text-[16px] font-medium text-blue-800"
                  >
                    Certificate Authority Code
                  </label>
                }
                rules={[
                  {
                    required: true,
                    message: "Please select certificate profile code",
                  },
                ]}
              >
                <Select size="large" id="certificateAuthorityCode" onChange={(value) => handleFetchData({certificateAuthorityInfo: true, certificatePurposeInfo: true})}>
                  <Option value={null}>{loading ? <Spin/> : '----'}</Option>
                  {data.certificateAuthorityInfo &&
                    data.certificateAuthorityInfo.map((item, index) => (
                      <Option key={index} value={item.certificateAuthorityCode}>
                        {item.certificateAuthorityName}
                      </Option>
                    ))}
                </Select>
              </Form.Item>
              <Form.Item
                name="certificateProfileCode"
                label={
                  <label
                    htmlFor="certificateProfileCode"
                    className="block text-[16px] font-medium text-blue-800"
                  >
                    Certificate Profile Code
                  </label>
                }
                rules={[
                  {
                    required: true,
                    message: "Please select certificate profile code",
                  },
                ]}
              >
                <Select size="large" id="certificateProfileCode">
                  <Option value={null}>{loading ? <Spin/> : '----'}</Option>
                  {data.certificateProfileInfo &&
                    data.certificateProfileInfo.map((item, index) => (
                      <Option key={index} value={item.certificateProfileCode}>
                        {item.certificateProfileName}
                      </Option>
                    ))}
                </Select>
              </Form.Item>
              <Form.Item
                name="certificatePurposeCode"
                label={
                  <label
                    htmlFor="certificatePurposeCode"
                    className="block text-[16px] font-medium text-blue-800"
                  >
                    Certificate Purpose Code
                  </label>
                }
                rules={[
                  {
                    required: true,
                    message: "Please select certificate purpose code",
                  },
                ]}
              >
                <Select size="large" id="certificatePurposeCode">
                  <Option value={null}>{loading ? <Spin/> : '----'}</Option>
                  {data.certificatePurposeInfo &&
                    data.certificatePurposeInfo.map((item, index) => (
                      <Option key={index} value={item.certificatePurposeCode}>
                        {item.certificatePurposeName}
                      </Option>
                    ))}
                </Select>
              </Form.Item>
              <Form.Item
                name="phoneContact"
                label={
                  <label
                    htmlFor="phoneContact"
                    className="block text-[16px] font-medium text-blue-800"
                  >
                    Phone Contact
                  </label>
                }
                rules={[
                  {
                    required: true,
                    message: "Please input phone contact",
                  },
                  {
                    validator: (_, value) => {
                      if (!value) return Promise.resolve();
                      if (isValidPhoneNumber(value) === false) {
                        return Promise.reject(
                          new Error("Invalid phone number")
                        );
                      }
                      return Promise.resolve();
                    },
                  },
                ]}
              >
                <Input
                  size="large"
                  className="w-full"
                  id="phoneContact"
                  placeholder="ex: 0392007203, +84392007203"
                  type="number"
                  onWheel={handleInputNumber}
                />
              </Form.Item>
              <Form.Item
                name="emailContact"
                label={
                  <label
                    htmlFor="emailContact"
                    className="block text-[16px] font-medium text-blue-800"
                  >
                    Email Contact
                  </label>
                }
                rules={[
                  {
                    required: true,
                    message: "Please input email contact",
                  },
                  {
                    validator: (_, value) => {
                      if (!value) return Promise.resolve();
                      if (isValidEmail(value) === false) {
                        return Promise.reject(
                          new Error("Invalid email address")
                        );
                      }
                      return Promise.resolve();
                    },
                  },
                ]}
              >
                <Input
                  size="large"
                  className=""
                  id="emailContact"
                  placeholder="ex: someone@gmail.com"
                />
              </Form.Item>
            </div>
            <Form.Item>
              <Button
                aria-label="Sign-in"
                htmlType="submit"
                role="button"
                size="large"
                className="rounded-md bg-blue-800"
                loading={loading}
                type="primary"
              >
                Register
              </Button>
            </Form.Item>
          </Form>
        </div>
      </div>
    </AuthLayout>
  );
}
