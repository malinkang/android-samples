package com.malinkang.recyclerview.customlayoutmanager.layoutmanager;

public class FlowLayoutOptions {
	public static final int ITEM_PER_LINE_NO_LIMIT = 0;
	public Alignment alignment = Alignment.LEFT; //默认居左
	public int itemsPerLine = ITEM_PER_LINE_NO_LIMIT; //限制每行的item数目，0为不限制

	public static FlowLayoutOptions clone(FlowLayoutOptions layoutOptions) {
		FlowLayoutOptions result = new FlowLayoutOptions();
		result.alignment = layoutOptions.alignment;
		result.itemsPerLine = layoutOptions.itemsPerLine;
		return result;
	}
}
