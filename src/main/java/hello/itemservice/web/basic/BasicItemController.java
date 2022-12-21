package hello.itemservice.web.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;

@Controller
@RequestMapping("/basic/items")
public class BasicItemController {

	private final ItemRepository itemRepository;

//	@Autowired
	public BasicItemController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@GetMapping
	public String items(Model model) {
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);

		return "/basic/items";
	}

	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);

		return "/basic/item";
	}

	@GetMapping("/add")
	public String addForm() {
		return "/basic/addForm";
	}

//	@PostMapping("/add")
	public String saveV1(@RequestParam String itemName, 
					   @RequestParam Integer price, 
					   @RequestParam int quantity,
					   Model model) {
		Item item = new Item(itemName, price, quantity);
		
		itemRepository.save(item);
		
		model.addAttribute("item", item);
		
		return "basic/item";
	}

//	@PostMapping("/add")
	public String saveV2(@ModelAttribute("item") Item item, Model model) {

		itemRepository.save(item);

//		model.addAttribute("item", item);

		return "basic/item";
	}
	
//	@PostMapping("/add")
	public String saveV3(@ModelAttribute Item item) {
		
		itemRepository.save(item);
		
		return "basic/item";
	}
	
	@PostMapping("/add")
	public String saveV4(Item item) {
		
		itemRepository.save(item);
		
		return "basic/item";
	}
	
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		
		return "basic/editForm";
	}
	
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
		itemRepository.update(itemId, item);
		
		return "redirect:/basic/items/{itemId}";
	}
	

	/**
	 * 테스트용 데이터 추가
	 */
	@PostConstruct
	public void init() {
		itemRepository.save(new Item("itemA", 10000, 10));
		itemRepository.save(new Item("itemB", 20000, 25));
	}

}
