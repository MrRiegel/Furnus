package mrriegel.furnus.gui;

import mrriegel.furnus.Furnus;
import mrriegel.furnus.block.TileFurnus;
import mrriegel.furnus.handler.OpenMessage;
import mrriegel.furnus.handler.PacketHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class IOFGui extends GuiScreen {
	private static final ResourceLocation GuiTextures = new ResourceLocation(
			Furnus.MODID + ":textures/gui/iof.png");
	TileFurnus tile;
	GuiButton top, front, left, right, bottom, back;
	int imageWidth = 100;
	int imageHeight = 100;
	int guiLeft, guiTop;
	String id;

	public enum Mode {
		NORMAL, AUTO, X;
		private static Mode[] vals = values();

		public Mode next() {
			return vals[(this.ordinal() + 1) % vals.length];
		}
	}

	Mode topMode = Mode.NORMAL;
	Mode frontMode = Mode.NORMAL;
	Mode leftMode = Mode.NORMAL;
	Mode rightMode = Mode.NORMAL;
	Mode bottomMode = Mode.NORMAL;
	Mode backMode = Mode.NORMAL;

	public IOFGui(TileFurnus tileEntity, int iD) {
		tile = tileEntity;
		if (iD == 1)
			id = "I";
		else if (iD == 2)
			id = "O";
		else if (iD == 3)
			id = "F";
		// System.out.println("id: "+id);
		// System.out.println("t: "+tile);
		// System.out.println("c; "+tile.getConfigs());
		// System.out.println("a: "+tile.getConfigs().get(id));
		// topMode = tile.getConfigs().get(id).get(0);
		// frontMode = tile.getConfigs().get(id).get(1);
		// leftMode = tile.getConfigs().get(id).get(2);
		// rightMode = tile.getConfigs().get(id).get(3);
		// bottomMode = tile.getConfigs().get(id).get(4);
		// backMode = tile.getConfigs().get(id).get(5);
	}

	@Override
	public void initGui() {
		super.initGui();
		guiLeft = (this.width - this.imageWidth) / 2;
		guiTop = (this.height - this.imageHeight) / 2;
		top = new GuiButton(0, guiLeft + 40, guiTop + 20, 20, 20, topMode
				.toString().substring(0, 1).toUpperCase());
		buttonList.add(top);
		front = new GuiButton(1, guiLeft + 40, guiTop + 42, 20, 20, frontMode
				.toString().substring(0, 1).toUpperCase());
		buttonList.add(front);
		left = new GuiButton(2, guiLeft + 18, guiTop + 42, 20, 20, leftMode
				.toString().substring(0, 1).toUpperCase());
		buttonList.add(left);
		right = new GuiButton(3, guiLeft + 62, guiTop + 42, 20, 20, rightMode
				.toString().substring(0, 1).toUpperCase());
		buttonList.add(right);
		bottom = new GuiButton(4, guiLeft + 40, guiTop + 64, 20, 20, bottomMode
				.toString().substring(0, 1).toUpperCase());
		buttonList.add(bottom);
		back = new GuiButton(5, guiLeft + 62, guiTop + 64, 20, 20, backMode
				.toString().substring(0, 1).toUpperCase());
		buttonList.add(back);
	}

	@Override
	protected void actionPerformed(GuiButton p_146284_1_) {
		switch (p_146284_1_.id) {
		case 0:
			topMode = topMode.next();
			top.displayString = topMode.toString().substring(0, 1)
					.toUpperCase();
			break;
		case 1:
			frontMode = frontMode.next();
			front.displayString = frontMode.toString().substring(0, 1)
					.toUpperCase();
			break;
		case 2:
			leftMode = leftMode.next();
			left.displayString = leftMode.toString().substring(0, 1)
					.toUpperCase();
			break;
		case 3:
			rightMode = rightMode.next();
			right.displayString = rightMode.toString().substring(0, 1)
					.toUpperCase();
			break;
		case 4:
			bottomMode = bottomMode.next();
			bottom.displayString = bottomMode.toString().substring(0, 1)
					.toUpperCase();
			break;
		case 5:
			backMode = backMode.next();
			back.displayString = backMode.toString().substring(0, 1)
					.toUpperCase();
			break;
		}
	}

	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
		drawDefaultBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GuiTextures);
		guiLeft = (this.width - this.imageWidth) / 2;
		guiTop = (this.height - this.imageHeight) / 2;
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, this.imageWidth,
				this.imageHeight);

		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void onGuiClosed() {
		// mc.thePlayer.openGui(Furnus.instance, 0, mc.theWorld, tile.xCoord,
		// tile.yCoord, tile.zCoord);
		PacketHandler.INSTANCE.sendToServer(new OpenMessage(tile.xCoord,
				tile.yCoord, tile.zCoord));
	}
}
