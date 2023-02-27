package com.eoem.ali.sms;

import cn.hutool.json.JSONUtil;
import com.aliyun.tea.TeaException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class Sample {
    
    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
    
    public static void main(String[] args_) throws Exception {
        log.debug("### main start {}", LocalDateTime.now());
        java.util.List<String> args = java.util.Arrays.asList(args_);
        // 工程代码泄露可能会导致AccessKey泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html
        com.aliyun.dysmsapi20170525.Client client = Sample.createClient("xx", "xx");
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setPhoneNumbers("xx")
                .setSignName("xx")
                .setTemplateCode("xx")
                .setTemplateParam("{\"status\":\"1\"}");
        
        try {
            // 复制代码运行请自行打印 API 的返回值
            com.aliyun.dysmsapi20170525.models.SendSmsResponse res = client.sendSmsWithOptions(sendSmsRequest, new com.aliyun.teautil.models.RuntimeOptions());
            log.debug("res:{}", JSONUtil.toJsonStr(res));
        } catch (TeaException error) {
            // 如有需要，请打印 error
            log.debug("### TeaException {}", error.getMessage());
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            log.debug("### Exception {}", _error.getMessage());
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
    }
}