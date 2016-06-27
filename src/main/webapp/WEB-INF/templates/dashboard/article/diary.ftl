<@c.input id="title" label="标题" val="${article.title!''}" empty="有标题可以让别人更容易看到..." required="true"/>
<@c.input id="summary" label="摘要" val="${article.summary!''}" empty="用简短的话概括主要内容..."/>
<@c.textarea id="body" label="内容" rows="16" val="${article.body!''}" empty="随手写一句吧..." required="true"/>

