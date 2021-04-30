#! /usr/bin/env python

import argparse
import subprocess as sp
from datetime import datetime

from git import Repo


def git_add(repo: Repo):
    repo.git.add(".")


def git_clean(repo: Repo):
    repo.git.clean("-fdX")


def git_commit(repo: Repo, msg):
    repo.index.commit(msg)


def git_pull(repo: Repo):
    repo.remotes.origin.pull()


def git_push(repo: Repo):
    repo.remotes.origin.push()


if __name__ == "__main__":
    parser = argparse.ArgumentParser()

    parser.add_argument("--add", action="store_true")
    parser.add_argument("--clean", action="store_true")
    parser.add_argument("--commit", action="store_true")
    parser.add_argument("--pull", action="store_true")
    parser.add_argument("--push", action="store_true")

    flags = parser.parse_args()

    bot = "🤖 R3nTru3W4n9"
    today = datetime.now()
    shortmsg = f"{bot} commits on {today}"
    longmsg = f"""
    {shortmsg}

    Beep Boop, I'm a bot. I haven't really written all this code by myself. The short commit message is a little bit confusing. @r3ntru3w4n9 is the one who writes the code, and runs me periodically or when he/she/it sees fits.
    If you feel rather strongly for @r3ntru3w4n9 writing a more professional message or clairfy things or you just want to fix things, please reach out to him/her/it or comment on the part where you don't like. He/She/It is going to get a notification everytime you do so. And hopefully he/she/it listens to your suggestions! Beep Boop, message over!
    I am a bot, and this action is performed automatically. The source code can be found at https://github.com/r3ntru3w4n9/tmp/git.py
    """

    repo = Repo(".")

    if flags.clean:
        git_clean(repo)

    if flags.pull:
        git_pull(repo)

    if flags.add:
        git_add(repo)

    if flags.commit:
        git_commit(repo, longmsg)

    if flags.push:
        git_push(repo)
