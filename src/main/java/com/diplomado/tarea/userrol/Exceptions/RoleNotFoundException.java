package com.diplomado.tarea.userrol.Exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException() {
        super("Role not found");
    }
}

