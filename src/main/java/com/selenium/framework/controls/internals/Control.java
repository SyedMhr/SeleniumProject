package com.selenium.framework.controls.internals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

import com.selenium.framework.controls.api.ImplementedBy;


@ImplementedBy(ControlBase.class)
public interface Control extends WebElement, WrapsElement, Locatable {
    ControlBase Wait();

    ControlBase WaitForVisible();

    ControlBase Click();

    ControlBase ScrollToElement();
}
