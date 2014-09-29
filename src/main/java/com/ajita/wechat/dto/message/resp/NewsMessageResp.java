package com.ajita.wechat.dto.message.resp;

import java.util.List;

/**
 * 文本消息
 * 
 */
public class NewsMessageResp extends BaseMessageResp {
	public NewsMessageResp() {
		this.setMsgType("news");
	}

	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> item;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getItem() {
		return item;
	}

	public void setArticles(List<Article> item) {
		this.item = item;
	}
}
