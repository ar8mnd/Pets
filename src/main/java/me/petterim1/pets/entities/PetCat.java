package me.petterim1.pets.entities;

import cn.nukkit.Player;
import cn.nukkit.entity.custom.CustomEntity;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.IChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.nbt.tag.CompoundTag;
import me.petterim1.pets.EntityPet;
import me.petterim1.pets.Utils;
import org.jetbrains.annotations.NotNull;
import cn.nukkit.entity.data.EntityFlag;
import cn.nukkit.entity.data.EntityDataTypes;

public class PetCat extends EntityPet implements CustomEntity {

    protected int type;

    public PetCat(IChunk chunk, CompoundTag nbt) {
        super(chunk, nbt);
    }

    @Override
    protected void initEntity() {
        super.initEntity();

        this.sitting = this.namedTag.getBoolean("Sitting");
        this.setDataFlag(EntityFlag.SITTING, this.isSitting());

        this.type = this.namedTag.getInt("CatType");
        this.setDataProperty(EntityDataTypes.VARIANT, this.type);

        this.pitch = 100;
    }

//    @Override
//    public int getNetworkId() {
//        return EntityCat.NETWORK_ID;
//    }

    @Override
    public float getWidth() {
        return 0.6f;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "minecraft:petcat";
    }

    @Override
    public float getHeight() {
        return 0.7f;
    }

    @Override
    protected boolean isFeedItem(String id) {
        return id == Item.TROPICAL_FISH || id == Item.SALMON;
    }

    @Override
    public boolean onInteract(Player player, Item item, Vector3 clickedPos) {
        if (this.isFeedItem(item.getId())) {
            this.feed(player, item);
            return true;
        }
        if (this.isOwner(player)) {
            this.setSitting();
        }
        return super.onInteract(player, item, clickedPos);
    }

    @Override
    public void setRandomType() {
        this.type = Utils.rand(1, 3);
        this.setDataProperty(EntityDataTypes.VARIANT, this.type);
        this.saveNBT();
    }

    @Override
    public void saveNBT() {
        super.saveNBT();

        this.namedTag.putInt("CatType", this.type);
        this.namedTag.putBoolean("Sitting", this.isSitting());
    }

    @Override
    protected String getType() {
        return "'s cat";
    }

    @Override
    protected String getSaveName() {
        return "cat";
    }
}