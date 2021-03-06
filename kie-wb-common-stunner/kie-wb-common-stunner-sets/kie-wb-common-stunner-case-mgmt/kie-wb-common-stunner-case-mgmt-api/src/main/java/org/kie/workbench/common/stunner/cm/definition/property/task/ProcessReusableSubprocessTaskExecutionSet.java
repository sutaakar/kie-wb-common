/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.workbench.common.stunner.cm.definition.property.task;

import javax.validation.Valid;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.jboss.errai.databinding.client.api.Bindable;
import org.kie.workbench.common.forms.adf.definitions.annotations.FieldParam;
import org.kie.workbench.common.forms.adf.definitions.annotations.FormDefinition;
import org.kie.workbench.common.forms.adf.definitions.annotations.FormField;
import org.kie.workbench.common.forms.adf.definitions.annotations.field.selector.SelectorDataProvider;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.basic.selectors.listBox.type.ListBoxFieldType;
import org.kie.workbench.common.stunner.bpmn.definition.BPMNPropertySet;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.CalledElement;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.Independent;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.IsAsync;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.OnEntryAction;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.OnExitAction;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.ScriptTypeListValue;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.ScriptTypeValue;
import org.kie.workbench.common.stunner.bpmn.definition.property.task.WaitForCompletion;
import org.kie.workbench.common.stunner.cm.definition.property.subprocess.Case;
import org.kie.workbench.common.stunner.core.definition.annotation.Property;
import org.kie.workbench.common.stunner.core.definition.annotation.PropertySet;
import org.kie.workbench.common.stunner.core.util.HashUtil;

@Portable
@Bindable
@PropertySet
@FormDefinition(
        startElement = "calledElement"
)
public class ProcessReusableSubprocessTaskExecutionSet implements BPMNPropertySet {

    @Property
    @SelectorDataProvider(
            type = SelectorDataProvider.ProviderType.REMOTE,
            className = "org.kie.workbench.common.stunner.bpmn.backend.dataproviders.CalledElementFormProvider")
    @FormField(
            type = ListBoxFieldType.class
    )
    @Valid
    protected CalledElement calledElement;

    @Property
    @FormField(
            readonly = true,
            afterElement = "calledElement"
    )
    @Valid
    protected Case caze;

    @Property
    @FormField(
            afterElement = "caze"
    )
    @Valid
    protected Independent independent;

    @Property
    @FormField(
            afterElement = "independent"
    )
    @Valid
    protected WaitForCompletion waitForCompletion;

    @Property
    @FormField(
            afterElement = "waitForCompletion"
    )
    @Valid
    protected IsAsync isAsync;

    @Property
    @FormField(afterElement = "isAsync",
            settings = {@FieldParam(name = "mode", value = "ACTION_SCRIPT")}
    )
    @Valid
    private OnEntryAction onEntryAction;

    @Property
    @FormField(afterElement = "onEntryAction",
            settings = {@FieldParam(name = "mode", value = "ACTION_SCRIPT")}
    )
    @Valid
    private OnExitAction onExitAction;

    public ProcessReusableSubprocessTaskExecutionSet() {
        this(new CalledElement(),
             new Case(false),
             new Independent(),
             new WaitForCompletion(),
             new IsAsync(),
             new OnEntryAction(new ScriptTypeListValue().addValue(new ScriptTypeValue("java", ""))),
             new OnExitAction(new ScriptTypeListValue().addValue(new ScriptTypeValue("java", ""))));
    }

    public ProcessReusableSubprocessTaskExecutionSet(final @MapsTo("calledElement") CalledElement calledElement,
                                                     final @MapsTo("caze") Case caze,
                                                     final @MapsTo("independent") Independent independent,
                                                     final @MapsTo("waitForCompletion") WaitForCompletion waitForCompletion,
                                                     final @MapsTo("isAsync") IsAsync isAsync,
                                                     final @MapsTo("onEntryAction") OnEntryAction onEntryAction,
                                                     final @MapsTo("onExitAction") OnExitAction onExitAction) {
        this.calledElement = calledElement;
        this.caze = caze;
        this.independent = independent;
        this.waitForCompletion = waitForCompletion;
        this.isAsync = isAsync;
        this.onEntryAction = onEntryAction;
        this.onExitAction = onExitAction;
    }

    public CalledElement getCalledElement() {
        return calledElement;
    }

    public void setCalledElement(final CalledElement calledElement) {
        this.calledElement = calledElement;
    }

    public Case getCaze() {
        return caze;
    }

    public void setCaze(Case caze) {
        this.caze = caze;
    }

    public Independent getIndependent() {
        return independent;
    }

    public WaitForCompletion getWaitForCompletion() {
        return waitForCompletion;
    }

    public void setIndependent(final Independent independent) {
        this.independent = independent;
    }

    public void setWaitForCompletion(final WaitForCompletion waitForCompletion) {
        this.waitForCompletion = waitForCompletion;
    }

    public IsAsync getIsAsync() {
        return isAsync;
    }

    public void setIsAsync(IsAsync isAsync) {
        this.isAsync = isAsync;
    }

    public OnEntryAction getOnEntryAction() {
        return onEntryAction;
    }

    public void setOnEntryAction(final OnEntryAction onEntryAction) {
        this.onEntryAction = onEntryAction;
    }

    public OnExitAction getOnExitAction() {
        return onExitAction;
    }

    public void setOnExitAction(final OnExitAction onExitAction) {
        this.onExitAction = onExitAction;
    }

    @Override
    public int hashCode() {
        return HashUtil.combineHashCodes(calledElement.hashCode(),
                                         caze.hashCode(),
                                         independent.hashCode(),
                                         waitForCompletion.hashCode(),
                                         isAsync.hashCode(),
                                         onEntryAction.hashCode(),
                                         onExitAction.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ProcessReusableSubprocessTaskExecutionSet) {
            ProcessReusableSubprocessTaskExecutionSet other = (ProcessReusableSubprocessTaskExecutionSet) o;
            return calledElement.equals(other.calledElement) &&
                    caze.equals(other.caze) &&
                    independent.equals(other.independent) &&
                    waitForCompletion.equals(other.waitForCompletion) &&
                    isAsync.equals(other.isAsync) &&
                    onEntryAction.equals(other.onEntryAction) &&
                    onExitAction.equals(other.onExitAction);
        }
        return false;
    }

}
