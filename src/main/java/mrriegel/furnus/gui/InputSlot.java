package mrriegel.furnus.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class InputSlot extends Slot {
	private EntityPlayer thePlayer;

	public InputSlot(EntityPlayer p_i1813_1_, IInventory p_i1824_1_,
			int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		this.thePlayer = p_i1813_1_;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Slot e = ((Slot) obj);
		return e.xDisplayPosition == xDisplayPosition
				&& e.yDisplayPosition == yDisplayPosition;
	}

}
