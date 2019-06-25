package com.jeesite.modules.app.utils.config;

public class AliAppPayConfig {
	//appid实际用的
	public static final String APPID = "2019042964382047";
	//appid 测试用的
	 //public static final String APPID = "2016092900627066";
	 
	    /** 支付宝网关 (实际用的)*/					
	    public static final String GATE = "https://openapi.alipay.com/gateway.do";
	    /**支付宝网关测试用的*/
	   // public static final String GATE = "https://openapi.alipaydev.com/gateway.do";
	 
	    /** (应用私钥) 实际用的*/
	    public static final String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCQYLpIUD7toFhtEti3q7WGzcjgHE9SmTKgmVH60P3OYv1VdpUb0a4HP3v9mhxCCTL8GIY7t5I0AQFKmNts2C+lhHWODtdS2T46Q2TKknQUSiyKJLCyi7mkvMjNfg5dJEo/NTaJ9plLfEbdQSjngdy02Kmp0LxRPCjANNYHi+32tf7CZ4Nj9Vxt4RJiw9ZeDdAwOmLjR1NKOD63XhWXCrabNiPJWSZAErdODU0hPOvMseoI9Lw68Gdhdn0Uyby9r2QDK6KWEjuowyPPQpTs7tCZ3Y4RefnHHKyhcTLAwNw9AQRA4Qxi6SIkmMkW1HjAaXPQSV8QnnFKqHYWy3c2FydhAgMBAAECggEAQuDrqY+ydrSuAWiXhw09O0Kb6GR0whhBAW/EL+EbkLz+CNazX32TlbvsnZ8Ds/s66NgbZhI04W4NwRZpnlPOTme7ZKCvUatcTfTl/Yg1yShK1XsFds+OoSbqBXZ9BNxIoFrv9pyKihL5aIhEgGkBaaxre0Q71418evx+oGRLSNp2vP/T5PK67YYlHpH4xuuFrmdyyCOR3IOkZCwQ1CYCVhxVTgcZUJkB3b+m51WHqNJ5zx3ybpXHlMLMk827CjbWKLOwtNrkUJuYseEN3xJ3/6pdKrUF602edmy6A+1dqZKP1cOzq7x7ix8fu6oL49xgXmCN5IKJHfqqUeFNe4kuAQKBgQDM6LGpfV1IxQb9P/xxvEP4VGzmmVPQzaDGGFRsQtPk3VZw5jca55Av9ufhpAFeh41kbq8dxXBAR/Vy+F6kqvsDp+ITbY/s7+igjpXhSs4U+hbEO7rPZZ3dQTBS5cn/xv7ls73Rd0VIb01MkO3oYXej9EIaW4mVbVgLF+i85b0DEQKBgQC0YFeVN+FKYru8fDlKamZiAPbp7zIL8GTMw6phXMqj980ET3TCACqJtkm5wNNVBV+YmLSdMIlCNvKmhAUaEEs7+1dggQFT8A4GiZGrJiFqVOy+1GVaG3C6u3w4V7TAkxUUv/CsJIaYUwgI0djwweiVqt/z7hDqXUppTfQwWQg/UQKBgDSvxDazUFAMsR5e3HcWFFf+xT2JwR5YMPWefd6UVE1th/NhjluxnMZocvj2pY97xSREvWwnPxSYLAlSyWsHt6EaAqxCKJagnSzjXLBf/pswLrhoL0FyfGPOhjFaelT1AsdnLvBz/XGBhNuiDDoLDasve+uxC7BLPC8mB6bCX5YxAoGAcYjOuALTUMHyz0IVwYxVY0yeHkNL6g3nNKfYP65rcmfQaO0mXdJC9CmEIGKJ4tMeB+vei654RvBVLZCdsXkeEeQzbG/nCRFpkp3WzbaTg91uovMH1H6/JxpJpyE1iZ1KwKPL9l6LRafFv99uQKSdJE/I5T4s8YfFBaFmmWQA9/ECgYA+FBl67XRnB55xmOlwYv+JkIEzCxp7iFkpWOIXjapQYzJHZ5V8FKavuj2yllMjPByJFjRQ5Ak+bdp+5lxj/mgQqQ7EvHmCBDmPlAARnJ7eedFv99CA+HSzJ3LTnoGPv4Kdsj1AxRusDL4jqOPe7c73NJVGfyyQdSL2B4GO1mLBtQ==";
	    /** (应用私钥) 测试用的*/
	    //public static final String APP_PRIVATE_KEY =   "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtWzojwCwpDUNe8MbtbHWzAYw+Za8iEMaGviG+Rw0Vtazc4KAatb3hcD9zML9jOicOj2FhkYjT12cEpWYfM/+P88ZcjkQcMXzqIx6NKYbNmOSwzhNsfI59PP4DM6veihZl6+vmt6wfaWMKkkFHoWS7nN7CPUAiBrSFiRk34fuj+tkvpvXj5HORSsS7UHC/VkcxxmWuErBS2rCKrSlyOYz+eftJh2ary1ZzVl84ej8HzLmAPrA+vxVCe4TpL3XbYBkFzgIAB0gGrbG4zWuLRS257rSfz2/iy/vW7frnZdROK/QBgcSS67K4UggE2T2lnAF2n/LnaoJFnWlQTZC4N5IpAgMBAAECggEAY7xtqa3hekjYLuImAOj6ml9UmX2VEetG3LS84yUroQbiCj3tvxA6y13yE0rDgcouOFFDfIfnicGO0nx6UY9wBBkDUbLD+th5/pbv99Uhal4BjLszL6JFtEYH+j2bLzCRDFh8Y0Pe8j+GrMlSZ2yycMIi4uusXycuavlimeUZCtDLoB/MT02Fs5/baUonaOQ3V716T+uoNM/msnuARf3wvCbnEczUl3Qu4fRWwKIxMfpuh/67JcvDSDUOf/pZRE2J0iGCO5d8cU+zWzmChmURGZMTsFWV2pbeTItxhQ0bUdhBra7DkKit5mpQ1ZEb2nfE3lWWzFfQcFkeIwcS2hvkgQKBgQD+zRP5YkyFNzk2SQK+lHqOGOy/UtjUwFGaHiAW+QmyxHV8OiKZ1smIn4OTnc7C9rjNQ+7CdIb8eNiN3MmkTNarv9K/26Dahf0Y9En02y6yJEYfuNOx1CkDUa12QxFY9WJQ5gP9+3k96/bNJCyNIicN6La3jQwrHcbgDVE5C5pvcQKBgQCuLAtecd65xcYy75nPEfolOk20CNr6y1tEq/3ES49IDAcRbKtVh+uGdGpHaynrufvnmitP1ZCowOOr40t5NOxBNGM0M9rjf+ozmNgTor/G3pxtM3PETWhrIBUqmjIcUFZoeuNmaiw27KjabRN5QZgO0E2PZP8t+dQIye7ykCPiOQKBgGfJkzRG5RJyTxycmsCUbGNLJcw869OlTtfvI3UYFeo6efyTFZsCUW8UQp0zb5AcvWPnIu8UVhOrCdKnN390SREGfsrlpFy+rTyn7SQOz7OCcQnk2F4cJGSUtYpxgTRyJ4z6VG/1Kgqd/hbLgFYwriXNP/f+PRGm/GYxJn+NCWgRAoGBAKAbovPV722Mzg7CzPxaRT31o7ptLYfAaoztIiFRaExNqi2tXK8KfEskNxKiA25LkLKtuUTxSMKoVmILVrT8HnYuR0eUp5HcY5m81lzujiatOtTzqGb61ZMsceiwyIHYpCX1WZcq4Kw4z1vDS1R7JOHxQGsh56YG85CEuOBH+oSZAoGBALGTumyxe/1qO2SwYS/wk/LEdF3nZR0MJ00pPjGe7uO0Fhs0HNtjFZwRg+0x909D479/RYD+fQHNZRKxQa7+K5F0rNAneVejYYFIOtINi5DwII/ny962eNxszJgsbf3cGCdpBLOwcew8cN1zi4AqUa9lF3zbMiISKT8vbsQcNlkW";
	 
	    /** （应用公钥）实际用的*/
	    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkGC6SFA+7aBYbRLYt6u1hs3I4BxPUpkyoJlR+tD9zmL9VXaVG9GuBz97/ZocQgky/BiGO7eSNAEBSpjbbNgvpYR1jg7XUtk+OkNkypJ0FEosiiSwsou5pLzIzX4OXSRKPzU2ifaZS3xG3UEo54HctNipqdC8UTwowDTWB4vt9rX+wmeDY/VcbeESYsPWXg3QMDpi40dTSjg+t14Vlwq2mzYjyVkmQBK3Tg1NITzrzLHqCPS8OvBnYXZ9FMm8va9kAyuilhI7qMMjz0KU7O7Qmd2OEXn5xxysoXEywMDcPQEEQOEMYukiJJjJFtR4wGlz0ElfEJ5xSqh2Fst3NhcnYQIDAQAB";
	    /** （应用公钥）测试用的*/
	    //public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArVs6I8AsKQ1DXvDG7Wx1swGMPmWvIhDGhr4hvkcNFbWs3OCgGrW94XA/czC/YzonDo9hYZGI09dnBKVmHzP/j/PGXI5EHDF86iMejSmGzZjksM4TbHyOfTz+AzOr3ooWZevr5resH2ljCpJBR6Fku5zewj1AIga0hYkZN+H7o/rZL6b14+RzkUrEu1Bwv1ZHMcZlrhKwUtqwiq0pcjmM/nn7SYdmq8tWc1ZfOHo/B8y5gD6wPr8VQnuE6S9122AZBc4CAAdIBq2xuM1ri0Utue60n89v4sv71u3652XUTiv0AYHEkuuyuFIIBNk9pZwBdp/y52qCRZ1pUE2QuDeSKQIDAQAB";

	 
	    /**  支付宝公钥 实际用的 */
	    public static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlS7Qp+TGBYtswpOn8f6O3EfQLvLTTa/EtB8g9mqhB8GBh658oGgMJoSKyRdnJioq/iiMKM7AstQ3cGQ6+MroY5zMKywWl/RoLYh3DnKclvFyRtzUinYZSPCMv/EbM85MX93HtfoBlZoNw6mzSvwMIthv0AZGTzvkpgeGhMxT4V3/X/WD7cezkgcLH8RGfLhQT234NThKO+5y5Bq4mi3v+IJ1+GIJLY0c7xr84dtTl9uYMvoov/Ux5yFGMeVvYB0M0Ba+89uFHjbthD+hbI3oE54hr86SwlqXfSuw5UbMnQHfxCQ2AjObDNSix8IQNmS3vwXMczC9oW2epOc/Y64a3QIDAQAB";
	    /**  支付宝公钥 测试用的 */
	    //public static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAm0RYbEbe3YgOQlpJd73cGClSF0OqNyBGNKqiAyT52EvE1y4kvIDorWUb90yrhj8ZXHLbuysBy8SV7akdAa4qfbbxSCw07NbS9ziQ2btfLV+BtHKgpfwBCju8/G2tDyb9eSv/ozEDTtNIx/oEV8juKfbAR1Rv9HOyWfKwntugeBdVB44j+2UyoNIg1gdYOIUfxAe2io7msWRJjg0IHMMcS80MIz2Re0eUS09eKICZpQI2fazBUF5YqZpLfN3cY1KpTe2poyZZHVFuroZc0MwHPQzaz7aZX0kucbFEL8fePYwDOGvdHowtzAWPZjuKCNEJV3OIWUOVxejcCDZUvtlQXQIDAQAB";
	    
	    /** 合作伙伴ID（支付宝给定） *//*
	    public static final String SELLERID = "此处为sellerid，支付宝针对每个apk都有特定的id";*/
	    
	    /** 商户账户 */
	    public static final String SELLER = "此处为商户账号";
	    
	    /** 编码方式 */
	    public static final String CHARSET = "utf-8";
	    
		public AliAppPayConfig() {
		}



}
