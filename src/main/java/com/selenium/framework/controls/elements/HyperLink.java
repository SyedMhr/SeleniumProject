package com.selenium.framework.controls.elements;

import com.selenium.framework.controls.api.ImplementedBy;
import com.selenium.framework.controls.internals.Control;
import com.selenium.framework.controls.internals.ControlBase;



@ImplementedBy(HyperLinkBase.class)
public interface HyperLink extends Control{


    void ClickLink();
    String GetUrlText();
    boolean CheckUrlTextContains(String containsText);
    //ControlBase Wait();
    ControlBase WaitForVisible();
    ControlBase Click();
}
