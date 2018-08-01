package com.controller.entrust;

import com.common.QueryAction;
import com.model.TwoDInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("twoDInfoAction")
public class TwoDInfoController extends QueryAction<TwoDInfo> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /*//二维码ID
    private String strTwoDInfoId = "";*/

    @RequestMapping("twoDInfo.action")
    public String twoDInfo() {
        return "twoDInfo";
    }

    /*public String getStrTwoDInfoId() {
        return strTwoDInfoId;
    }

    public void setStrTwoDInfoId(String strTwoDInfoId) {
        this.strTwoDInfoId = strTwoDInfoId;
    }*/

}
