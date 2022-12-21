package hello.itemservice.domain.item;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.itemservice.domain.Item;

class ItemRepositoryTest {

	ItemRepository itemRepository = new ItemRepository();

	@AfterEach
	void afterEach() {
		itemRepository.clearStore();
	}

	@Test
	void save() {
		// given
		Item item = new Item("itemA", 10000, 10);

		// when
		Item savedItem = itemRepository.save(item);

		// then
		Item findItem = itemRepository.findById(item.getId());
		assertThat(findItem).isEqualTo(savedItem);
	}

	@Test
	void findAll() {
		// given
		Item item1 = new Item("itemA", 10000, 10);
		Item item2 = new Item("itemB", 20000, 20);
		
		itemRepository.save(item1);
		itemRepository.save(item2);

		// when
		List<Item> findItems = itemRepository.findAll();

		// then
		assertThat(findItems.size()).isEqualTo(2);
		assertThat(findItems).contains(item1, item2);
	}

	@Test
	void update() {
		// given
		Item item1 = new Item("itemA", 10000, 10);
		
		Item savedItem = itemRepository.save(item1);
		Long itemId = savedItem.getId();
		
		// when
		Item updateParam = new Item("itemC", 25000, 35);
		itemRepository.update(itemId, updateParam);
		
		// then
		Item findItem = itemRepository.findById(itemId);
		
		assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
		assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
		assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());

	}

}
