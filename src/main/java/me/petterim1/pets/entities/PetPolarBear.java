package me.petterim1.pets.entities;

import cn.nukkit.Player;
import cn.nukkit.entity.passive.EntityPolarBear;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import me.petterim1.pets.EntityPet;

public class PetPolarBear extends EntityPet {

    public PetPolarBear(FullChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    protected void initEntity() {
        super.initEntity();

        this.setDataFlag(DATA_FLAGS, DATA_FLAG_BABY, true);
        this.setScale(0.6f);
    }

    @Override
    public int getNetworkId() {
        return EntityPolarBear.NETWORK_ID;
    }

    @Override
    public float getWidth() {
        return 0.65f;
    }

    @Override
    public float getHeight() {
        return 1f;
    }

    @Override
    protected boolean isFeedItem(int id) {
        return id == Item.RAW_FISH || id == Item.RAW_SALMON || id == Item.CLOWNFISH;
    }
    
    @Override
    public boolean onInteract(Player player, Item item, Vector3 clickedPos) {
        if (this.isFeedItem(item.getId())) {
            this.feed(player, item);
            return true;
        }
        return super.onInteract(player, item, clickedPos);
    }

    @Override
    protected String getType() {
        return "'s polar bear";
    }

    @Override
    protected String getSaveName() {
        return "PolarBear";
    }
}
