package org.marker.weixin.msg;

import org.marker.weixin.WXXmlElementName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 图片消息
 * @author marker
 * @date 2013-8-25 上午8:53:37
 * @version 1.0
 * @blog www.yl-blog.com
 * @weibo http://t.qq.com/wuweiit
 */
public class Msg4Image extends Msg{

	//位0x0001被标志时，星标刚收到的消息。
	private String funcFlag;
	// 图片消息媒体id
	private String mediaId;

	/**
	 * 开发者调用
	 * */
	public Msg4Image() {
		this.head = new Msg4Head();
		this.head.setMsgType(Msg.MSG_TYPE_IMAGE);//设置消息类型
	}
	
	
	/**
	 * 程序内部调用
	 * */
	public Msg4Image(Msg4Head head) {
		this.head = head;
	}

	@Override
	public void write(Document document) {
		Element root = document.createElement(WXXmlElementName.ROOT);
		head.write(root, document);
		
		Element mediaIdElement = document.createElement(WXXmlElementName.MEDIAID);
		mediaIdElement.setTextContent(this.mediaId);
		
		Element imageElement = document.createElement(WXXmlElementName.IMAGE);
		imageElement.appendChild(mediaIdElement);
		
		Element funcFlagElement = document.createElement(WXXmlElementName.FUNC_FLAG);
		funcFlagElement.setTextContent(this.funcFlag);
		
		root.appendChild(imageElement);
		root.appendChild(funcFlagElement);
		document.appendChild(root);
	}
	
	@Override
	public void read(Document document) {
		this.mediaId = getElementContent(document, WXXmlElementName.MEDIAID);
	}

	public String getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	
	
}
