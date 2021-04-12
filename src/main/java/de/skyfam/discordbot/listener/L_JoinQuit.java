package main.java.de.skyfam.discordbot.listener;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class L_JoinQuit extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Member member = event.getMember();

        /* Join Role */
        event.getGuild().addRoleToMember(member.getUser().getId(), event.getGuild().getRoleById("660514557214588957")).queue();

    }
}
