package com.vendingmachine;

public class ContainerList<Obj1, Obj2> {
	private Obj1 first;
    private Obj2 second;
    
	public ContainerList(Obj1 first, Obj2 second) {
		super();
		this.first = first;
		this.second = second;
	}

	public Obj1 getFirst() {
		return first;
	}

	public void setFirst(Obj1 first) {
		this.first = first;
	}

	public Obj2 getSecond() {
		return second;
	}

	public void setSecond(Obj2 second) {
		this.second = second;
	}
    
    
}
