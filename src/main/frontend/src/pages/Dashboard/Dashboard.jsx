import { useEffect, useState } from "react";
import AuthLayout from "../../layouts/AuthLayout";
import { Form, Select } from "antd";
import Axios from "../../utils/Axios";

const initDataState = {
  certificateProfileInfo: [],
};

const { Option } = Select;

export default function Dashboard() {
  const [data, setData] = useState(initDataState);

  useEffect(() => {
    handleFetchData().then((data) => {
      setData(data);
    });
  }, []);

  const handleFetchData = async () => {
    let data = {};

    const params = new URLSearchParams({
      language: 0,
    });

    const certificateProfileInfo = await Axios.get(
      "/certificate/getCertificateProfileForTMSRA?" + params.toString()
    );
    data["certificateProfileInfo"] = certificateProfileInfo.data.data;
    return data;
  };

  return (
    <AuthLayout>
      <div className="pt-[50px] md:pt-[80px]">
        <div className="w-full lg:max-w-[1400px] p-5 lg:p-16 bg-white mx-auto">
          <Form>
            <Form.Item label="" name="certificateProfileCode">
              <Select>
                {data.certificateProfileInfo &&
                  data.certificateProfileInfo.map((item, index) => (
                    <Option key={index} value={item.certificateProfileCode}>{item.certificateProfileName}</Option>
                  ))}
              </Select>
            </Form.Item>
          </Form>
        </div>
      </div>
    </AuthLayout>
  );
}
