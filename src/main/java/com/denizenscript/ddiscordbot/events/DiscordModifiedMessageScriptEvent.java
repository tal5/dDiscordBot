package com.denizenscript.ddiscordbot.events;

import net.aufdemrand.denizencore.events.ScriptEvent;
import net.aufdemrand.denizencore.objects.Element;
import net.aufdemrand.denizencore.objects.dObject;
import net.aufdemrand.denizencore.scripts.containers.ScriptContainer;
import net.aufdemrand.denizencore.utilities.CoreUtilities;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEditEvent;

public class DiscordModifiedMessageScriptEvent extends ScriptEvent {
    public static DiscordModifiedMessageScriptEvent instance;

    // <--[event]
    // @Events
    // discord modified message (by <bot>)
    //
    // @Regex ^on discord modified message(by [^\s]+)?$
    //
    // @Triggers when a Discord user modified a message.
    //
    // @Plugin dDiscordBot
    //
    // @Context
    // <context.bot> returns the ID of the bot.
    // <context.group> returns the group ID.
    // <context.group_name> returns the group name.
    // <context.author_id> returns the author's internal ID.
    // <context.author_name> return's the author's name.
    // <context.new_message> return's the new message.
    // <context.old_message> return's the old message.
    //
    // -->

    @Override
    public boolean couldMatch(ScriptContainer scriptContainer, String s) {
        return CoreUtilities.toLowerCase(s).startsWith("discord modified message");
    }

    @Override
    public boolean matches(ScriptContainer scriptContainer, String s) {
        String lower = CoreUtilities.toLowerCase(s);
        if (lower.equals("discord modified message")) {
            return true;
        }
        else if (CoreUtilities.xthArgEquals(4, lower, botID)) {
            return true;
        }
        return false;
    }

    public String botID;

    public MessageEditEvent mre;

    @Override
    public dObject getContext(String name) {
        if (name.equals("bot")) {
            return new Element(botID);
        }
        else if (name.equals("channel")) {
            return new Element(mre.getChannel().getLongID());
        }
        else if (name.equals("channel_name")) {
            return new Element(mre.getChannel().getName());
        }
        else if (name.equals("group")) {
            return new Element(mre.getGuild().getLongID());
        }
        else if (name.equals("group_name")) {
            return new Element(mre.getGuild().getName());
        }
        else if (name.equals("new_message")) {
            return new Element(mre.getNewMessage().toString());
        }
        else if (name.equals("old_message")) {
            return new Element(mre.getOldMessage().toString());
        }
        else if (name.equals("author_id")) {
            return new Element(mre.getAuthor().getLongID());
        }
        else if (name.equals("author_name")) {
            return new Element(mre.getAuthor().getName());
        }
        return super.getContext(name);
    }

    @Override
    public String getName() {
        return "DiscordModifiedMessage";
    }

    boolean enab = false;

    @Override
    public void init() {
        enab = true;
    }

    @Override
    public void destroy() {
        enab = false;
    }
}