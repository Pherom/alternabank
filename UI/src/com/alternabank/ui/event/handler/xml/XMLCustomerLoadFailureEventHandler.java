package com.alternabank.ui.event.handler.xml;

import com.alternabank.engine.xml.event.XMLCustomerLoadFailureEvent;
import com.alternabank.engine.xml.event.listener.XMLCustomerLoadFailureListener;
import com.alternabank.ui.form.YesNoForm;
import com.alternabank.ui.form.error.ErrorDialogue;
import com.alternabank.ui.form.error.xml.XMLCustomerLoadErrorDialogue;

public class XMLCustomerLoadFailureEventHandler implements XMLCustomerLoadFailureListener {

    @Override
    public void customerLoadFailed(XMLCustomerLoadFailureEvent event) {
        ErrorDialogue errorDialogue = new XMLCustomerLoadErrorDialogue(event);
        errorDialogue.display();
        if(errorDialogue.getResults() == YesNoForm.Option.NO) {
            event.getSource().stopLoading();
        }
    }

}
