package com.selenium.framework.controls.elements;

import com.selenium.framework.controls.api.ImplementedBy;
import com.selenium.framework.controls.internals.Control;
import com.selenium.framework.controls.internals.ControlBase;


@ImplementedBy(ButtonBase.class)
public interface Button extends Control {

    void PerformClick();
    String GetButtonText();
    void PerformSubmit();
    ControlBase Wait();
    ControlBase WaitForVisible();
    ControlBase Click();
    ControlBase ScrollToElement();
}
