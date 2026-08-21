---
name: dev-server-ports
description: Start this project's backend (Spring Boot, port 8080) or frontend (Vite, port 5173) dev server without ever falling back to an alternate port. Use this whenever asked to start, run, restart, or check on the backend or frontend dev server, or whenever a bootRun / npm run dev command reports its target port is already in use (EADDRINUSE, "port already in use", etc.).
---

# Dev server ports

This project's backend and frontend must always bind to their fixed ports:

- Backend (`./gradlew bootRun`, from `backend/`) → **8080**
- Frontend (`npm run dev`, from `frontend/`) → **5173**

These ports are load-bearing, not arbitrary defaults: the backend's CORS
config (`backend/src/main/java/com/taskmanagement/backend/config/CorsConfig.java`)
allows only `http://localhost:5173` as an origin, and the frontend's
`VITE_API_BASE_URL` (`frontend/.env`) points at `http://localhost:8080`.
If either server starts on a different port because its usual one was
busy, the two stop being able to talk to each other — API calls fail
with a CORS error or a connection error that has nothing to do with
the actual cause, which is confusing to debug later.

## What to do

Before starting either server, check whether its port is already
occupied. If it is, **stop that process and reuse the same port** —
never let the tool pick a substitute port (e.g. Vite silently moving
to 5174) and never proceed on the substitute.

### Windows (PowerShell)

```powershell
$conn = Get-NetTCPConnection -LocalPort 8080 -State Listen -ErrorAction SilentlyContinue
if ($conn) { Stop-Process -Id $conn.OwningProcess -Force }
```

Swap `8080` for `5173` for the frontend.

### Git Bash

```bash
pid=$(netstat -ano | grep ":8080 " | grep LISTENING | awk '{print $NF}' | head -1)
[ -n "$pid" ] && powershell -Command "Stop-Process -Id $pid -Force"
```

Swap `8080` for `5173` for the frontend.

Then start the server as usual:

```bash
cd backend && ./gradlew bootRun    # port 8080
cd frontend && npm run dev         # port 5173
```

Confirm it actually bound to the right port before moving on (e.g.
poll `curl -s -o /dev/null -w "%{http_code}" http://localhost:8080/api/cards`
or `http://localhost:5173/`) rather than assuming success from the
process starting.
