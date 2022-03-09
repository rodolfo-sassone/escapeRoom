/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.escaperoom;

import java.util.List;

/**
 *
 * @author Rodolfo Pio Sassone
 */
public class ContainerObject extends MyObject {
    
    private List<MyObject> Objects;

    public ContainerObject(int id, String name, String description) {
        super(id, name, description);
    }

    public ContainerObject(List<MyObject> Objects, int id, String name, String description) {
        super(id, name, description);
        this.Objects = Objects;
    }

    public ContainerObject(List<MyObject> Objects, int id, String name, String description, boolean openable, boolean pushable, boolean pickupable, boolean open, boolean push, int keyValue, Lock Blocked) {
        super(id, name, description, openable, pushable, pickupable, open, push, keyValue, Blocked);
        this.Objects = Objects;
    }

    public List<MyObject> getObjects() {
        if(isOpenable() && isOpen() || !isOpenable())
            return Objects;
        else
            return null;
    }

    public void setObjects(List<MyObject> Objects) {
        this.Objects = Objects;
    }
    
    
}
