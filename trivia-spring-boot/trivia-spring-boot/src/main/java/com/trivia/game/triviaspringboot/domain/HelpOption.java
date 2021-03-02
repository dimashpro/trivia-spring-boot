package com.learning.game.domain;

abstract class HelpOption {

    private boolean isUsed = false;
    private boolean isInvoked = false;

    abstract void invoke(Question question);

    public boolean isUsed() {

        return isUsed;
    }

    public void setUsed(boolean used) {

        isUsed = used;
    }

    public boolean isInvoked() {

        return isInvoked;
    }

    public void setInvoked(boolean invoked) {

        isInvoked = invoked;
    }
}
