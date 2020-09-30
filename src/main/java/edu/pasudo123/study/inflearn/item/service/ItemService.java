package edu.pasudo123.study.inflearn.item.service;

import edu.pasudo123.study.inflearn.item.model.Item;
import edu.pasudo123.study.inflearn.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public long saveItem(final Item item) {
        itemRepository.save(item);
        return item.getId();
    }

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Item findById(final Long id) {
        return itemRepository.findById(id);
    }
}
