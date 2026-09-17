---
name: squash-merge
description: Finish a branch by squash merging it straight into `main` — the maintainer's default flow, used instead of opening a PR. Steps - pre-flight, offer code review, rebase onto main, run the gate (lintKotlin + testAndroidHostTest), squash into main, push, delete the branch. Documentation-only changes skip the gate and run a consistency self-check instead. Trigger on squash merge, merge to main, 合併到 main, 合併進 main, 收尾這個功能, 把這支分支併回 main, finish this feature, can this be merged. Use it whenever the user wants branch work folded into `main`, even if they never say "squash".
---

# Squash Merge to main

`main` is the development branch. A finished branch goes in as **one clean commit** — one change,
one commit, no merge bubbles.

## When NOT to use this

Open a pull request instead when:

- the change came from **an outside contributor** (their fork, their PR — review it, don't squash it
  in locally),
- the change is risky or wide enough that you want CI and a written review trail on it,
- someone other than the maintainer needs to sign off.

Everything else — the maintainer's own finished branch — takes this flow. If unsure, ask.

## Step 0: Pre-flight

```bash
git branch --show-current
git status --short
git fetch origin
git log main..HEAD --oneline
```

- On `main`, several candidate branches, or uncommitted work sitting on `main` → ask which branch to
  merge. One branch at a time.
- Uncommitted changes → commit them on the feature branch first; squash only takes what is committed.
- `git log main..HEAD` empty → nothing to merge. Report and stop.

## Step 1: Offer a code review

Ask with `AskUserQuestion` whether to review the branch before merging.

- **Yes** → run the review, resolve anything serious, then continue.
- **No** → straight to Step 2.

## Step 2: Rebase onto main

```bash
git switch main && git merge --ff-only origin/main
git switch <feature-branch> && git rebase main
```

Conflicts → stop and report; resolve together, then `git rebase --continue`.

Rebase before validating: the gate must run on what the merge will actually produce.

## Step 3: Gate

```bash
./gradlew lintKotlin :composeApp:testAndroidHostTest
```

`testAndroidHostTest` compiles and runs `commonTest` as well as the Android-host ViewModel tests, so
these two tasks are the whole local gate. Three-platform compilation is CI's job after the push.

- ktlint failure → `./gradlew formatKotlin`, re-run. `ignoreLintFailures = false`, so this is not
  optional.
- Test or compile failure → stop, report, fix, re-run. Never merge a red gate.

**Documentation-only changes** (only `*.md`, `docs/**`, `.claude/**`, `.github/**` — no Kotlin,
Gradle, or resource files) → skip the gate and do a consistency self-check instead:

- every path, task name, symbol and command referenced still exists (run the commands you document),
- no leftover references to something the change removed,
- documents state the final decision, with no "previously we did X" residue.

## Step 4: Squash merge

```bash
git switch main
git merge --squash <feature-branch>
git commit          # message format below
git push origin main
```

Conflicts → stop and report; resolve, `git add`, then commit.

## Step 5: Clean up

```bash
git branch -D <feature-branch>              # -d refuses after a squash; check `git diff main <branch>` is empty first
git push origin --delete <feature-branch>   # only if it was ever pushed
```

## Step 6: Report

Squash commit hash and subject, gate result, whether a review ran, branch deleted.

## Commit message

- `<type>: <English subject>`, Conventional Commits type (`feat` / `fix` / `refactor` / `docs` /
  `chore` / `style` / `perf` / `test` / `build` / `ci`).
- Subject ≤ 70 characters, sentence case, describing the end state — not "first X then Y".
- Body only when the change warrants it: what a reader would see in the merged code, not a replay of
  the work.
- Never concatenate the branch's WIP messages.
