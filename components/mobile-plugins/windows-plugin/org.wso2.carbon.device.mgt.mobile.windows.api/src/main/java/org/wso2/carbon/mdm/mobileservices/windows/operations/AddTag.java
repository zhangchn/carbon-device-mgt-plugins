/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.mdm.mobileservices.windows.operations;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wso2.carbon.mdm.mobileservices.windows.operations.util.Constants;

import java.util.Iterator;
import java.util.List;

/**
 * Configurations that needs to be added on the device.
 */
@ApiModel(value = "AddTag", description = "This class carries all information related to Syncml AddTag.")
public class AddTag {
    @ApiModelProperty(name = "commandId", value = "CommandID of the AddTag.", required = true)
    int commandId = -1;
    @ApiModelProperty(name = "items", value = "List of items of the AddTag.", required = true)
    List<Item> items;

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void buildAddElement(Document doc, Element rootElement) {
        if (getItems() != null) {
            Element add = doc.createElement(Constants.ADD);
            rootElement.appendChild(add);
            if (getCommandId() != -1) {
                Element commandId = doc.createElement(Constants.COMMAND_ID);
                commandId.appendChild(doc.createTextNode(String.valueOf(getCommandId())));
                add.appendChild(commandId);
            }
            for (Iterator<Item> itemIterator = getItems().iterator(); itemIterator.hasNext(); ) {
                Item item = itemIterator.next();
                if (item != null) {
                    item.buildItemElement(doc, add);
                }
            }
        }
    }
}
