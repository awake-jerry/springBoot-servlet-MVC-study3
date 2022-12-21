package hello.itemservice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
	private Long id;
	private String itemName;
	private Integer price; // Price 없을 경우를 가정하여 Integer
	private int quantity;

	public Item() {

	}

	public Item(String itemName, Integer price, int quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
}
